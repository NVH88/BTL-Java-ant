///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//
//package controllers.article;
//
//import dal.articleDAO.ArticleDAO;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// *
// * @author Hanh
// */
//@WebServlet(name="DeleteArticle", urlPatterns={"/article/delete_articleOld"})
//public class DeleteArticle extends HttpServlet {
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
//            JSONObject jsonObject = new JSONObject(json.toString());
//            int article_id = jsonObject.getInt("article_id");
//            ArticleDAO ad = new ArticleDAO();
//            ad.deleteObject(article_id);
//                       
//            String jsonString = "{\"message\": \"delete successfully\"}";
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(jsonString);
//        } catch (JSONException ex) {
//            Logger.getLogger(DeleteArticle.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}