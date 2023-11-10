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
    private int commentId, likes, dislikes, articleId, userId;
    private String commentContent;
    private Date commentTime;

    public Comment(int commentId, int likes, int dislikes, String commentContent, 
            Date commentTime, int articleId, int userId) {
        this.commentId = commentId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.articleId = articleId;
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getArticleId() {
        return articleId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", likes=" + likes + ", dislikes=" + dislikes + ", articleId=" + articleId + ", userId=" + userId + ", commentContent=" + commentContent + ", commentTime=" + commentTime + '}';
    }
}