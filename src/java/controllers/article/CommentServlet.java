/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Article;
import Model.Article.Comment;
import Model.User.User;
import dal.articleDAO.ArticleDAO;
import dal.articleDAO.CommentDAO;
import dal.userDAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Hanh
 */
@WebServlet(name="CommentServlet", urlPatterns={"/article/comment"})
public class CommentServlet extends HttpServlet {
    
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
            int user_id = jsonObject.getInt("user_id");
            int article_id = jsonObject.getInt("article_id");
            String comment_content = jsonObject.getString("content");
            
            // gọi user ở đây (getUserById)
            UserDAO ud = new UserDAO();
            User user = (User) ud.getById(user_id);
            Date today = new Date(System.currentTimeMillis());
            ArticleDAO ad = new ArticleDAO();
            Article art = (Article) ad.getById(article_id);
            
            Comment cmt = new Comment(0, 0, 0, comment_content, today, art, user);
            CommentDAO cd = new CommentDAO();
            cd.addObject(cmt);

            String jsonString = "{\"message\": \"comment successfully\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
        } catch (JSONException ex) {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}