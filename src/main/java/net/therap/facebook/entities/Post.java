package main.java.net.therap.facebook.entities;

import java.util.Date;
import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class Post {
    private int postId;
    private String content;
    private Date time;
    private UserInfo userInfo;
    private List<Comment> comments;
    private List<UserInfo> likes;

    public Post(UserInfo userInfo, String content, Date time) {
        this.userInfo = userInfo;
        this.content = content;
        this.time = time;
    }

    public Post(int postId, UserInfo userInfo, String content, Date time) {
        this.postId = postId;
        this.userInfo = userInfo;
        this.content = content;
        this.time = time;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<UserInfo> getLikes() {
        return likes;
    }

    public void setLikes(List<UserInfo> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", userInfo=" + userInfo +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }
}
