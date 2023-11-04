/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.articleDAO;

import Model.Article.Article;
import Model.User.User;
import dal.DAO.DAO;
import dal.userDAO.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hanh
 */
public class ArticleDAO extends DAO{

    @Override
    public Object getById(int Article_id) {
        String sql = "select * from articles where article_id = " + Article_id;
//        System.out.println(sql);
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDAO ud = new UserDAO();
                User user = (User) ud.getById(rs.getInt("user_id"));
                return new Article(rs.getInt("article_id"), rs.getInt("likes"),
                rs.getInt("dislikes"), rs.getInt("reports"), rs.getString("article_name"),
                rs.getString("article_category"), rs.getString("article_tag"),
                rs.getString("content"), rs.getString("image"), rs.getDate("time_submit"),
                rs.getDate("time_accept"), rs.getBoolean("stt"), user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean addObject(Object object) {
        try {
            Article art = (Article)object;
            String sql = "insert into articles(article_name, article_category, article_tag,"
                    + "content, image, time_submit, time_accept, likes, dislikes, reports,"
                    + "user_id, stt) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, art.getArticle_name());
            st.setString(2, art.getArticle_category());
            st.setString(3, art.getArticle_tag());
            st.setString(4, art.getContent());
            st.setString(5, art.getImage());
            st.setDate(6, art.getTime_submit());
            st.setDate(7, art.getTime_accept());
            st.setInt(8, art.getLikes());
            st.setInt(9, art.getDislikes());
            st.setInt(10, art.getReports());
            st.setInt(11, art.getUser().getUser_id());
            st.setBoolean(12, art.isStt());
            st.executeUpdate();
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {        
//            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateObject(Object object) {
        try {
            Article art = (Article)object;
            String sql = "update articles set article_name = ?, article_category =?,"
                    + " article_tag = ?, content = ?, image = ?,"
                    + " likes = ?, dislikes = ?, reports = ?, stt = ? where article_id = " 
                    + art.getArticle_id();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, art.getArticle_name());
            st.setString(2, art.getArticle_category());
            st.setString(3, art.getArticle_tag());
            st.setString(4, art.getContent());
            st.setString(5, art.getImage());
            st.setInt(6, art.getLikes());
            st.setInt(7, art.getDislikes());
            st.setInt(8, art.getReports());
            st.setBoolean(9, art.isStt());
            st.executeUpdate();
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteObject(int objectId) {
        try {
            String sql = "delete from articles where article_id = " + objectId;
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
//            System.out.println("scs");
            return true;
        } catch (SQLException e) {
//            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Object> getAllObjects() {
        return null;
    }
    
    public ArrayList<Article> getListArticleAccepted() {
        try {
            String sql = "select * from articles where stt = 1";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<Article> arr = new ArrayList<>();
            while (rs.next()) {
                UserDAO ud = new UserDAO();
                User user = (User) ud.getById(rs.getInt("user_id"));
                arr.add(new Article(rs.getInt("article_id"), rs.getInt("likes"),
                rs.getInt("dislikes"), rs.getInt("reports"), rs.getString("article_name"),
                rs.getString("article_category"), rs.getString("article_tag"),
                rs.getString("content"), rs.getString("image"), rs.getDate("time_submit"),
                rs.getDate("time_accept"), rs.getBoolean("stt"), user));
            }
            return arr;
//            System.out.println("scs");
        } catch (SQLException e) {
//            System.out.println(e);
        }
        return null;
    }
    public ArrayList<Article> getListArticleInQueue() {
        try {
            String sql = "select * from articles where stt = 0";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ArrayList<Article> arr = new ArrayList<>();
            while (rs.next()) {
                UserDAO ud = new UserDAO();
                User user = (User) ud.getById(rs.getInt("user_id"));
                arr.add(new Article(rs.getInt("article_id"), rs.getInt("likes"),
                rs.getInt("dislikes"), rs.getInt("reports"), rs.getString("article_name"),
                rs.getString("article_category"), rs.getString("article_tag"),
                rs.getString("content"), rs.getString("image"), rs.getDate("time_submit"),
                rs.getDate("time_accept"), rs.getBoolean("stt"), user));
            }
            return arr;
//            System.out.println("scs");
        } catch (SQLException e) {
//            System.out.println(e);
        }
        return null;
    }
}
