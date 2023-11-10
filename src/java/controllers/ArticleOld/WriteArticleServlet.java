///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controllers.article;
//
//import Model.Article.Article;
//import Model.User.User;
//import dal.articleDAO.ArticleDAO;
//import dal.userDAO.UserDAO;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.sql.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// *
// * @author Hanh
// */
//@WebServlet(name="WriteArticleServletOld", urlPatterns={"/article/write_old"})
//public class WriteArticleServlet extends HttpServlet {
//    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        try {
//            BufferedReader reader = request.getReader();
//            StringBuilder json = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                json.append(line);
//            }
//            
//            JSONObject jsonObject = new JSONObject(json.toString());
//            int user_id = jsonObject.getInt("user_id");
//            String article_name = jsonObject.getString("article_name");
//            String image = jsonObject.getString("image");
//            String content = jsonObject.getString("content");
//            String article_category = jsonObject.getString("article_category");
//            String article_tag = jsonObject.getString("article_tag");
//            
//            UserDAO ud = new UserDAO();
//            User user = (User) ud.getById(user_id);
//            Date today = new Date(System.currentTimeMillis());
//            boolean stt = false;         
//            if (user.getUser_role() == 2) {
//                stt = true;
//            }
//            Article art = new Article(0, 0, 0, 0, article_name, article_category,
//                    article_tag, content, image, today, today, stt, user);
//            ArticleDAO ad = new ArticleDAO();
//            ad.addObject(art);
//            String jsonString;
//            if (stt) {
//                jsonString = "{\"message\": \"Your article has been posted.\"}";
//            } else {
//                jsonString = "{\"message\": \"Your post has been sent, admin will review and approve your post. Thank you for your contribution.\"}";
//            }
//          
//            response.setContentType("application/json"); 
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(jsonString);
//        } catch (JSONException ex) {
//            Logger.getLogger(WriteArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}