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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
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
    
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader reader = req.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            
            JSONObject jsonObject = new JSONObject(json.toString());
            int article_id = jsonObject.getInt("article_id");
            int likes = jsonObject.getInt("likes");
            int dislikes = jsonObject.getInt("dislikes");
            
            ArticleDAO ad = new ArticleDAO();
            Article a = (Article) ad.getById(article_id);
            a.setLikes(likes);
            a.setDislikes(dislikes);
            ad.updateObject(a);
            Gson gson = new Gson();
            String jsonString = gson.toJson(a);
            
            resp.setContentType("application/json"); 
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonString);
        } catch (JSONException ex) {
            Logger.getLogger(ArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String category = request.getParameter("category");
        String uncensored = request.getParameter("uncensored");
        
        ArticleDAO ad = new ArticleDAO();
        Gson gson = new Gson();
        String json = "";
        if (id != null) { // lấy bởi id
            Article art = (Article) ad.getById(Integer.parseInt(id));
            json = gson.toJson(art);
        } else if (category != null) { // lấy bởi danh mục
            ArrayList<Article> arr = ad.getListArticle("category", category);
            json = gson.toJson(arr);
        } else if (uncensored != null) { // lấy những article đang chờ duyệt
            ArrayList<Article> arr = ad.getListArticle("uncensored", "0");
            json = gson.toJson(arr);
        } else { // lấy toàn bộ bài viết đã được duyệt
            ArrayList<Article> arr = ad.getListArticle("stt", "1");
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
            BufferedReader reader = request.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            
            JSONObject jsonObject = new JSONObject(json.toString());
            int user_id = jsonObject.getInt("user_id");
            String article_name = jsonObject.getString("article_name");
            String image = jsonObject.getString("image");
            String content = jsonObject.getString("content");
            String article_category = jsonObject.getString("article_category");
            String article_tag = jsonObject.getString("article_tag");
            
            UserDAO ud = new UserDAO();
            User user = (User) ud.getById(user_id);
            Date today = new Date(System.currentTimeMillis());
            boolean stt = false;         
            if (user.getUser_role() == 2) {
                stt = true;
            }
            Article art = new Article(0, 0, 0, 0, article_name, article_category,
                    article_tag, content, image, today, today, stt, user_id);
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
            Logger.getLogger(ArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}