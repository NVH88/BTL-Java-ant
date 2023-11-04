/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Article;
import Model.Article.ReactionArticle;
import Model.User.User;
import dal.articleDAO.ArticleDAO;
import dal.articleDAO.ReactionArticleDAO;
import dal.userDAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
@WebServlet(name="ArticleReact", urlPatterns={"/article/react"})
public class ReactionArticleServlet extends HttpServlet {   
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
            JSONObject jsonObject = new JSONObject(json);
            int article_id = jsonObject.getInt("article_id");
            int user_id = jsonObject.getInt("user_id");
            UserDAO ud = new UserDAO();
            ArticleDAO ad = new ArticleDAO();
            boolean reaction_type = jsonObject.getBoolean("reaction_type");
            ReactionArticleDAO rad = new ReactionArticleDAO();
            ReactionArticle ra = rad.getByArticleAndUser(article_id, user_id);
            
            if (ra == null) {
                ra = new ReactionArticle(0, reaction_type, (User)ud.getById(user_id), 
                        (Article)ad.getById(article_id));
                rad.addObject(ra);
            } else {
                if (reaction_type == ra.isReation_type()) {
                    rad.deleteObject(ra.getReaction_article_id());
                } else {
                    ra.setReation_type(!reaction_type);
                    rad.updateObject(ra);
                }
            }
            
            String jsonString = "{\"message\": \"successfully\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonString);
        } catch (JSONException ex) {
            Logger.getLogger(DeleteArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}