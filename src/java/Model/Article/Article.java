/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

import java.sql.Date;

/**
 *
 * @author Hanh
 */
public class Article {
    private int articleId, userId, likes, dislikes, reports;
    private String articleName, articleCategory, articleDescription, content, image;
    private Date timeSubmit, timeAccept;
    private boolean stt;

    public Article(int articleId, int likes, int dislikes, int reports, 
            String articleName, String articleCategory, String articleDescription, 
            String content, String image, Date timeSubmit, Date timeAccept, 
            boolean stt, int userId) {
        this.articleId = articleId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reports = reports;
        this.articleName = articleName;
        this.articleCategory = articleCategory;
        this.articleDescription = articleDescription;
        this.content = content;
        this.image = image;
        this.timeSubmit = timeSubmit;
        this.timeAccept = timeAccept;
        this.stt = stt;
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public int getUserId() {
        return userId;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getReports() {
        return reports;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public Date getTimeSubmit() {
        return timeSubmit;
    }

    public Date getTimeAccept() {
        return timeAccept;
    }

    public boolean isStt() {
        return stt;
    }

    public void setStt(boolean stt) {
        this.stt = stt;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "Article{" + "articleId=" + articleId + ", userId=" + userId + ", likes=" + likes + ", dislikes=" + dislikes + ", reports=" + reports + ", articleName=" + articleName + ", articleCategory=" + articleCategory + ", articleDescription=" + articleDescription + ", content=" + content + ", image=" + image + ", timeSubmit=" + timeSubmit + ", timeAccept=" + timeAccept + ", stt=" + stt + '}';
    }
}