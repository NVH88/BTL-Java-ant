/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

import com.google.gson.Gson;
import dal.articleDAO.ArticleDAO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class Test {
    public static void main(String[] args) throws ParseException, JSONException {
        ArticleDAO ad = new ArticleDAO();
        ArrayList<Article> list = ad.getListArticleAccepted();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);

//        Article art;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        art = new Article(1, 1, 1, 1, "hi", "hello", "hello", "hello",
//                "#", new Date(dateFormat.parse("2003-06-02").getTime()), 
//                new Date(dateFormat.parse("2003-06-02").getTime()), true, new UserFake(1));
//        System.out.println(art);
//        ad.addObject(art);

//        Article a = (Article)ad.getById(2);
//        a.setArticle_name("Ronaldo > Messi");
//        ad.updateObject(a);

//        ArrayList<Article> arr = ad.getListArticleInQueue();
//        System.out.println(arr.size());

//        String jsonStr = "{\n" +
//                "    \"user_id\": 6,\n" +
//                "    \"article_id\": 7,\n" +
//                "    \"content\": \"bai viet that hay va bo ich\"\n" +
//                "}";
//
//        // Phân tích chuỗi JSON thành một đối tượng JSONObject
//        JSONObject jsonObject = new JSONObject(jsonStr);
//        
//        System.out.println(jsonObject);
//
//        // Lấy giá trị từ JSON bằng các khóa (keys)
//        int userId = jsonObject.getInt("user_id");
//        int articleId = jsonObject.getInt("article_id");
//        String content = jsonObject.getString("content");
//
//        // In ra các giá trị
//        System.out.println("User ID: " + userId);
//        System.out.println("Article ID: " + articleId);
//        System.out.println("Content: " + content);
    }
}