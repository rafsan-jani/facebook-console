package main.java.net.therap.facebook.entities;

import java.util.Date;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class Comment {

    private int postId;
    private int commentId;
    private UserInfo userInfo;
    private String content;
    private Date time;

    public Comment(int commentId, int postId, UserInfo userInfo, String content, Date time) {
        this.commentId = commentId;
        this.postId = postId;
        this.userInfo = userInfo;
        this.content = content;
        this.time = time;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
