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
import java.io.BufferedReader;
import java.sql.Date;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Hanh
 */
@WebServlet(name="CommentServlet", urlPatterns={"/comment"})
public class CommentServlet extends HttpServlet {
    
    // lấy comment theo bài viết
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("article_id");
        CommentDAO cd = new CommentDAO();
        ArrayList<Comment> arr = cd.getAllCommentByArticleID(Integer.parseInt(id));
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }   
    
    // tạo comment
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JSONObject jsonObject = new JSONObject(json.toString());
            int userId = jsonObject.getInt("userId");
            int articleId = jsonObject.getInt("articleId");
            String commentContent = jsonObject.getString("commentContent");
            Date today = new Date(System.currentTimeMillis());
            
            Comment cmt = new Comment(0, 0, 0, commentContent, today, articleId, userId);
            CommentDAO cd = new CommentDAO();
            cd.addObject(cmt);

            String jsonString = "{\"message\": \"comment successfully\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
        } catch (JSONException ex) {
        }
    }
}