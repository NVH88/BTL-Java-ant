///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controllers.ArticleOld;
//
//import Model.Article.Article;
//import com.google.gson.Gson;
//import dal.articleDAO.ArticleDAO;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// *
// * @author DELL
// */
//@WebServlet(name="ApproveArticleServlet", urlPatterns={"/article/approveOld"})
//public class ApproveArticleServlet extends HttpServlet {
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
//            JSONObject jsonObject = new JSONObject(json.toString());
//            int article_id = jsonObject.getInt("article_id");
//            boolean stt = jsonObject.getBoolean("status");
//            
//            // gọi user ở đây (getUserById)
//            ArticleDAO ad = new ArticleDAO();
//            Article art = (Article) ad.getById(article_id);
//            System.out.println(art);
//            if (stt) {
//                art.setStt(true);
//            } else {
//                ad.deleteObject(art.getArticle_id());
//            }
////            ArrayList<Article> list = ad.getListArticleInQueue();
////            Gson gson = new Gson();
////            String json2 = gson.toJson(list);
////            response.setContentType("application/json");
////            response.setCharacterEncoding("UTF-8");
////            response.getWriter().write(json2); // cần xem phần user sẽ gửi gì?
//        } catch (JSONException ex) {
//            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
//}