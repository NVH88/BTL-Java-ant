/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Comment;
import com.google.gson.Gson;
import dal.articleDAO.CommentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Hanh
 */
@WebServlet(name="CommentServlet", urlPatterns={"/comment"})
public class CommentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("PATCH")) {
            this.doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String articleId = request.getParameter("articleId"); // lọc theo bài viết
        String id = request.getParameter("id"); // lọc theo CommentId
        String sortBy = request.getParameter("sortBy"); // sắp cmt trong bài viết xếp theo tiêu chí
        CommentDAO cd = new CommentDAO();
        Gson gson = new Gson();
        String json = "";
        
        if (id != null) { // Lấy comment theo id
            Comment cmt = (Comment) cd.getById(Integer.parseInt(id));
            json = gson.toJson(cmt);
        } 
        
        else if (articleId != null) { // lấy theo bài viết
            String criteria = "article_id = " + articleId;
            ArrayList<Comment> list = cd.getListComment(criteria); // mặc định
            if (sortBy != null) { // sắp xếp
                if (sortBy.equals("likes")) { // theo số like
                    Collections.sort(list, new Comparator<Comment>() {
                        @Override
                        public int compare(Comment o1, Comment o2) {
                            return o2.getLikes() - o1.getLikes();
                        }   
                    });
                } 
                
                else if (sortBy.equals("scores")) { // theo số điểm
                    Collections.sort(list, new Comparator<Comment>() {
                        @Override
                        public int compare(Comment o1, Comment o2) {
                            return (o2.getLikes() - o2.getDislikes()) - (o1.getLikes() - o1.getDislikes());
                        }   
                    });
                } 
                
                else if (sortBy.equals("newest")) { // theo thời gian mới nhất
                    Collections.sort(list, new Comparator<Comment>() {
                        @Override
                        public int compare(Comment o1, Comment o2) {
                            return o2.getCommentTime().compareTo(o1.getCommentTime());
                        }   
                    });
                }
            }
            json = gson.toJson(list);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }   
    
    // tạo comment
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            StringBuilder json = (StringBuilder) request.getAttribute("json");
            JSONObject jsonObject = new JSONObject(json.toString());
            int userId = jsonObject.getInt("userId");
            int articleId = jsonObject.getInt("articleId");
            String commentContent = jsonObject.getString("commentContent");
            Timestamp now = new Timestamp(System.currentTimeMillis());
            
            Comment cmt = new Comment(0, 0, 0, commentContent, now, articleId, userId);
            CommentDAO cd = new CommentDAO();
            cd.addObject(cmt);

            String jsonString = "{\"message\": \"comment successfully\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
        } catch (JSONException ex) {
        }
    }
    
    // cập nhật số like, dislike của comment
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder json = (StringBuilder) req.getAttribute("json");
        Gson gson = new Gson();
        String jsonString = "";
        try {
            JSONObject jsonObject = new JSONObject(json.toString());
            int commentId = jsonObject.getInt("commentId");
            int likes = jsonObject.getInt("likes");
            int dislikes = jsonObject.getInt("dislikes");
            CommentDAO cd = new CommentDAO();
            Comment cmt = (Comment) cd.getById(commentId);
            cmt.setLikes(likes);
            cmt.setDislikes(dislikes);
            cd.updateObject(cmt);
            jsonString = gson.toJson(cmt);
            
        } catch (JSONException e){
            jsonString = "json sai";
        }
        resp.setContentType("application/json"); 
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonString);
    }  

    // xóa cmt
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder json = (StringBuilder) req.getAttribute("json");
        Gson gson = new Gson();
        String jsonString = "";
        boolean ok = false;
        if (!ok) {
            try { // có người ấn nút xóa
                JSONObject jsonObject = new JSONObject(json.toString());
                int commentId = jsonObject.getInt("commentId");
                CommentDAO cd = new CommentDAO();
                cd.deleteObject(commentId);
                ok = true;
                jsonString = "{\"message\": \"delete successfully\"}";
            } catch (JSONException e){
            }
        }
        
        if (!ok) {
            try { // xóa theo article
                JSONObject jsonObject = new JSONObject(json.toString());
                boolean byArticle = jsonObject.getBoolean("byArticle");
                int articleId = jsonObject.getInt("articleId");
                CommentDAO cd = new CommentDAO();
                cd.deleteByArticleId(articleId);
                ok = true;
                jsonString = "{\"message\": \"delete successfully\"}";
            } catch (JSONException e){
            }
        }
        
        if (!ok) {
            try { // xóa theo user
                JSONObject jsonObject = new JSONObject(json.toString());
                boolean byUser = jsonObject.getBoolean("byUser");
                int userId = jsonObject.getInt("userId");
                CommentDAO cd = new CommentDAO();
                cd.deleteByUserId(userId);
                ok = true;
                jsonString = "{\"message\": \"delete successfully\"}";
            } catch (JSONException e){
            }
        }
        
        resp.setContentType("application/json"); 
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonString);
    }
}