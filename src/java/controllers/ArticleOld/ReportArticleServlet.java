///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controllers.article;
//
//import Model.Article.Article;
//import Model.Article.Report;
//import Model.User.User;
//import dal.articleDAO.ArticleDAO;
//import dal.articleDAO.ReportDAO;
//import dal.userDAO.UserDAO;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// *
// * @author Hanh
// */
//@WebServlet(name="ReportArticleOld", urlPatterns={"/article/report_article_old"})
//public class ReportArticleServlet extends HttpServlet {
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
//            JSONObject jsonObject = new JSONObject(json);
//            int user_id = jsonObject.getInt("user_id");
//            int article_id = jsonObject.getInt("article_id");
//            String content = jsonObject.getString("content");
//            UserDAO ud = new UserDAO();
//            ArticleDAO ad = new ArticleDAO();
//            ReportDAO rd = new ReportDAO();
//            Report rp = rd.getByUserAndArticle(user_id, article_id);
//            
//            if (rp == null) {
//                rp = new Report(0, (User) ud.getById(user_id), (Article) ad.getById(article_id), content);
//                rd.addObject(rp);
//            } else {
//                rd.updateObject(rp);
//            }
//            
//            String jsonString = "{\"message\": \"delete successfully\"}";
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(jsonString);
//        } catch (JSONException ex) {
//        }
//    }
//}