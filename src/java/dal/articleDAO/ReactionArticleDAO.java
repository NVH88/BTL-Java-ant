/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.articleDAO;

import Model.Article.Article;
import Model.Article.ReactionArticle;
import Model.User.User;
import dal.DAO.DAO;
import dal.userDAO.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hanh
 */
public class ReactionArticleDAO extends DAO{

    @Override
    public Object getById(int Record_id) {
        ArticleDAO ad = new ArticleDAO();
        try {
            String sql = "select * from reaction_articles where reaction_article_id = " + Record_id;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDAO ud = new UserDAO();
                User user = (User) ud.getById(rs.getInt("user_id"));
                return new ReactionArticle(rs.getInt("reaction_article_id"), rs.getBoolean("reaction_type"), 
                user, (Article)ad.getById(rs.getInt("article_id")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean addObject(Object object) {
        try {
            ReactionArticle react = (ReactionArticle)object;
            String sql = "insert into reaction_articles(user_id, article_id, reaction_type) "
                    + "value(?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, react.getUser().getUser_id());
            st.setInt(2, react.getArticle().getArticle_id());
            st.setBoolean(3, react.isReation_type());
            st.executeUpdate();
            System.out.println("scs");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateObject(Object object) {
    try {
            ReactionArticle react = (ReactionArticle)object;
            String sql = "update reaction_articles set reaction_type = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, react.isReation_type());
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
            String sql = "delete from reaction_articles where reaction_article_id = " + objectId;
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Object> getAllObjects() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ReactionArticle getByArticleAndUser(int article_id, int user_id) {
        try {
            ArticleDAO ad = new ArticleDAO();
            Article art = (Article) ad.getById(article_id);
            UserDAO ud = new UserDAO();
            User u = (User) ud.getById(user_id);
            String sql = "select * from reaction_articles where user_id = ? and article_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, article_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ReactionArticle(rs.getInt("reaction_article_id"), 
                        rs.getBoolean("reaction_type"), u, art);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReactionArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
