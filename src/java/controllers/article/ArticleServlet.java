/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Article;
import Model.User.User;
import com.google.gson.Gson;
import dal.articleDAO.ArticleDAO;
import dal.userDAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Hanh
 */
@WebServlet(name="ArticleServlet", urlPatterns={"/article"})
public class ArticleServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("PATCH")) {
            this.doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }
    
    // cập nhật số like, dislike của bài viết hoặc duyệt bài
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader reader = req.getReader();
//        StringBuilder json = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            json.append(line);
//        }
        StringBuilder json = (StringBuilder) req.getAttribute("json");
        
        ArticleDAO ad = new ArticleDAO();
        Gson gson = new Gson();
        String jsonString = "";

        try { // cập nhật số like, dislike của bài viết
            JSONObject jsonObject = new JSONObject(json.toString());
            int articleId = jsonObject.getInt("articleId");
            int likes = jsonObject.getInt("likes");
            int dislikes = jsonObject.getInt("dislikes");
            Article a = (Article) ad.getById(articleId);
            a.setLikes(likes);
            a.setDislikes(dislikes);
            ad.updateObject(a);
            jsonString = gson.toJson(a);
        } catch (JSONException e){    
            resp.setContentType("application/json"); 
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Json sai");
        }
        
        try { // duyệt bài viết
            JSONObject jsonObject = new JSONObject(json.toString());
            int articleId = jsonObject.getInt("articleId");
            boolean stt = jsonObject.getBoolean("stt");
            Article a = (Article) ad.getById(articleId);
            a.setStt(stt);
            ad.updateObject(a);
            jsonString = gson.toJson(a);
        } catch (JSONException e){      
            resp.setContentType("application/json"); 
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("Json sai");
        }

        resp.setContentType("application/json"); 
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonString);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id"); //article_id
        String category = request.getParameter("category");
        String uncensored = request.getParameter("uncensored"); // bài chưa duyệt
        String sortBy = request.getParameter("sortBy"); // sắp xếp theo tiêu chí nào?
        String userId = request.getParameter("userId"); // lấy những bài viết của user theo thứ tự mới đến cũ
        
        ArticleDAO ad = new ArticleDAO();
        Gson gson = new Gson();
        String json = "";
        if (id != null) { // lấy bởi id
            Article art = (Article) ad.getById(Integer.parseInt(id));
            json = gson.toJson(art);
        } else if (category != null) { // lấy bởi danh mục
            String criteria = "article_category = '" + category + "' and stt = 1";
            ArrayList<Article> arr = ad.getListArticle(criteria);
            json = gson.toJson(arr);
        } else if (uncensored != null) { // lấy những article đang chờ duyệt
            String criteria = "stt = 0";
            ArrayList<Article> arr = ad.getListArticle(criteria);
            json = gson.toJson(arr);
        } else if (sortBy != null) { // lấy những article theo thứ tự sắp xếp
            String criteria = "stt = 1";
            ArrayList<Article> arr = ad.getListArticle(criteria);
            
            if (sortBy.equals("likes")) { // sort theo like
                Collections.sort(arr, new Comparator<Article>() {
                    @Override
                    public int compare(Article o1, Article o2) {
                        return o2.getLikes() - o1.getLikes();
                    }   
                });
            } else if (sortBy.equals("scores")) { // sort theo điểm (likes - dislikes)
                Collections.sort(arr, new Comparator<Article>() {
                    @Override
                    public int compare(Article o1, Article o2) {
                        return (o2.getLikes() - o2.getDislikes()) - (o1.getLikes() - o1.getDislikes());
                    }   
                });
            } else if (sortBy.equals("newest")) { // sort theo bài mới nhất
                Collections.sort(arr, new Comparator<Article>() {
                    @Override
                    public int compare(Article o1, Article o2) {
                        return o2.getTimeAccept().compareTo(o1.getTimeAccept());
                    }   
                });
            }
            
            json = gson.toJson(arr);
        } else if(userId != null) { // lấy những bài viết đã được duyệt của userId theo thứ tự mới nhất
            String criteria = "user_id = " + userId + " and stt = 1";
            ArrayList<Article> arr = ad.getListArticle(criteria);
            Collections.sort(arr, new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o2.getTimeAccept().compareTo(o1.getTimeAccept());
                }   
            });
            json = gson.toJson(arr);
        } else { // lấy toàn bộ bài viết đã được duyệt
            String criteria = "stt = 1";
            ArrayList<Article> arr = ad.getListArticle(criteria);
            json = gson.toJson(arr);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }    
    
    // tạo bài viết
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
//            BufferedReader reader = request.getReader();
//            StringBuilder json = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                json.append(line);
//            } // da co o filter
            StringBuilder json = (StringBuilder) request.getAttribute("json");
            
            JSONObject jsonObject = new JSONObject(json.toString());
            int userId = jsonObject.getInt("userId");
            String articleName = jsonObject.getString("articleName");
            String image = jsonObject.getString("image");
            String content = jsonObject.getString("content");
            String articleCategory = jsonObject.getString("articleCategory");
            String articleDescription = jsonObject.getString("articleDescription");
            
            UserDAO ud = new UserDAO();
            User user = (User) ud.getById(userId);
            Date today = new Date(System.currentTimeMillis());
            boolean stt = false;         
            if (user.getUser_role() == 2) {
                stt = true;
            }
            Article art = new Article(0, 0, 0, 0, articleName, articleCategory,
                    articleDescription, content, image, today, today, stt, userId);
            ArticleDAO ad = new ArticleDAO();
            ad.addObject(art);
            String jsonString;
            if (stt) {
                jsonString = "{\"message\": \"Your article has been posted.\"}";
            } else {
                jsonString = "{\"message\": \"Your post has been sent, admin will review and approve your post. Thank you for your contribution.\"}";
            }
            
            response.setContentType("application/json"); 
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
        } catch (JSONException ex) {
            response.setContentType("application/json"); 
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Json sai");
        }
    }
    
    // xóa bài viết theo id
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
//            BufferedReader reader = request.getReader();
//            StringBuilder json = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                json.append(line);
//            }
            StringBuilder json = (StringBuilder) request.getAttribute("json");
            
            JSONObject jsonObject = new JSONObject(json.toString());
            int articleId = jsonObject.getInt("articleId");
            ArticleDAO ad = new ArticleDAO();
            ad.deleteObject(articleId);
            
            String jsonString = "{\"message\": \"Delete successfully.\"}";            
            response.setContentType("application/json"); 
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
        } catch (JSONException ex) {
            response.setContentType("application/json"); 
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Json sai");
        }
    }
}