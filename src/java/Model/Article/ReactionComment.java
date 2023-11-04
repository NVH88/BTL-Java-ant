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
public class ReactionComment {
    private int reaction_article_id;
    private boolean reation_type;
    private User user;
    private Comment comment;

    public ReactionComment(int reaction_article_id, boolean reation_type, User user, Comment comment) {
        this.reaction_article_id = reaction_article_id;
        this.reation_type = reation_type;
        this.user = user;
        this.comment = comment;
    }

    public int getReaction_article_id() {
        return reaction_article_id;
    }

    public boolean isReation_type() {
        return reation_type;
    }

    public User getUser() {
        return user;
    }

    public Comment getComment() {
        return comment;
    }

    public void setReation_type(boolean reation_type) {
        this.reation_type = reation_type;
    }

    @Override
    public String toString() {
        return "ReactionComment{" + "reaction_article_id=" + reaction_article_id + ", reation_type=" + reation_type + ", user=" + user + ", comment=" + comment + '}';
    }

}