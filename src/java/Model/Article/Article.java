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
public class Article {
    private int article_id, likes, dislikes, reports;
    private String article_name, article_category, article_tag, content, image;
    private Date time_submit, time_accept;
    private boolean stt;
    private User user; // fake

    public Article(int article_id, int likes, int dislikes, int reports, 
            String article_name, String article_category, String article_tag, 
            String content, String image, Date time_submit, Date time_accept, 
            boolean stt, User user) {
        this.article_id = article_id;
        this.likes = likes;
        this.dislikes = dislikes;
        this.reports = reports;
        this.article_name = article_name;
        this.article_category = article_category;
        this.article_tag = article_tag;
        this.content = content;
        this.image = image;
        this.time_submit = time_submit;
        this.time_accept = time_accept;
        this.stt = stt;
        this.user = user;
    }

    public int getArticle_id() {
        return article_id;
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

    public String getArticle_name() {
        return article_name;
    }

    public String getArticle_category() {
        return article_category;
    }

    public String getArticle_tag() {
        return article_tag;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public Date getTime_submit() {
        return time_submit;
    }

    public Date getTime_accept() {
        return time_accept;
    }

    public boolean isStt() {
        return stt;
    }

    public User getUser() {
        return user;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }
    
    @Override
    public String toString() {
        return "Article{" + "article_id=" + article_id + ", likes=" + likes + ", dislikes=" + dislikes + ", reports=" + reports + ", article_name=" + article_name + ", article_category=" + article_category + ", article_tag=" + article_tag + ", content=" + content + ", image=" + image + ", time_submit=" + time_submit + ", time_accept=" + time_accept + ", stt=" + stt + ", user=" + user + '}';
    }
    
    
}