/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

import Model.User.User;
import java.sql.Date;

/**
 *
 * @author Hanh
 */
public class Comment {
    private int comment_id, likes, dislikes;
    private String comment_content;
    private Date comment_time;
    private Article article;
    private User user; // fake

    public Comment(int comment_id, int likes, int dislikes, String comment_content, 
            Date comment_time, Article article, User user) {
        this.comment_id = comment_id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comment_content = comment_content;
        this.comment_time = comment_time;
        this.article = article;
        this.user = user;
    }

    public int getComment_id() {
        return comment_id;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public String getComment_content() {
        return comment_content;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public Article getArticle() {
        return article;
    }

    public User getUser() {
        return user;
    }
    
    public void setComment_content(String s) {
        this.comment_content = s;
    }

    @Override
    public String toString() {
        return "Comment{" + "comment_id=" + comment_id + ", likes=" + likes + ", dislikes=" + dislikes + ", comment_content=" + comment_content + ", comment_time=" + comment_time + ", article=" + article + ", user=" + user + '}';
    }
    
    
}
