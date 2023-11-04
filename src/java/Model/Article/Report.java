/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Article;

import Model.User.User;

/**
 *
 * @author Hanh
 */
public class Report {
    private int report_id;
    private User user;
    private Article article;
    private String content;

    public Report(int report_id, User user, Article article, String content) {
        this.report_id = report_id;
        this.user = user;
        this.article = article;
        this.content = content;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Report{" + "report_id=" + report_id + ", user=" + user + ", article=" + article + ", content=" + content + '}';
    }
}