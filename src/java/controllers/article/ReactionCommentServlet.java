/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.article;

import Model.Article.Comment;
import Model.Article.ReactionComment;
import Model.User.User;
import dal.articleDAO.CommentDAO;
import dal.articleDAO.ReactionCommentDAO;
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
@WebServlet(name="ArticleReact", urlPatterns={"/article/reaction_comment"})
public class ReactionCommentServlet extends HttpServlet {   
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
            int comment_id = jsonObject.getInt("comment_id");
            int user_id = jsonObject.getInt("user_id");
            UserDAO ud = new UserDAO();
            CommentDAO ad = new CommentDAO();
            boolean reaction_type = jsonObject.getBoolean("reaction_type");
            ReactionCommentDAO rcd = new ReactionCommentDAO();
            ReactionComment rc = rcd.getByCommentAndUser(comment_id, user_id);
            
            if (rc == null) {
                rc = new ReactionComment(0, reaction_type, (User)ud.getById(user_id), 
                        (Comment)ad.getById(comment_id));
                rcd.addObject(rc);
            } else {
                if (reaction_type == rc.isReation_type()) {
                    rcd.deleteObject(rc.getReaction_article_id());
                } else {
                    rc.setReation_type(!reaction_type);
                    rcd.updateObject(rc);
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