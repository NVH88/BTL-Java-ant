/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.articleDAO;

import Model.Article.Comment;
import Model.Article.ReactionComment;
import Model.User.User;
import dal.DAO.DAO;
import dal.userDAO.UserDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hanh
 */
public class ReactionCommentDAO extends DAO{

    @Override
    public Object getById(int Record_id) {
        CommentDAO cd = new CommentDAO();
        try {
            String sql = "select * from reaction_comments where reaction_comment_id = " + Record_id;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDAO ud = new UserDAO();
                User user = (User) ud.getById(rs.getInt("user_id"));
                return new ReactionComment(rs.getInt("reaction_comment_id"), rs.getBoolean("reaction_type"), 
                user, (Comment) cd.getById(rs.getInt("comment_id")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean addObject(Object object) {
        try {
            ReactionComment react = (ReactionComment)object;
            String sql = "insert into reaction_comments(user_id, comment_id, reaction_type) "
                    + "value(?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, react.getUser().getUser_id());
            st.setInt(2, react.getComment().getComment_id());
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
            ReactionComment react = (ReactionComment)object;
            String sql = "update reaction_comments set reaction_type = ?";
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
            String sql = "delete from reaction_comments where reaction_comment_id = " + objectId;
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
    
    public ReactionComment getByCommentAndUser(int comment_id, int user_id) {
        try {
            CommentDAO cd = new CommentDAO();
            Comment cmt = (Comment) cd.getById(comment_id);
            UserDAO ud = new UserDAO();
            User u = (User) ud.getById(user_id);
            String sql = "select * from reaction_comments where user_id = ? and comment_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, comment_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ReactionComment(rs.getInt("reaction_comment_id"), 
                        rs.getBoolean("reaction_type"), u, cmt);
            }
        } catch (SQLException ex) {
        }
        return null;
    }
}