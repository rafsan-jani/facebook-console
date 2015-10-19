package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.entities.Comment;
import main.java.net.therap.facebook.entities.Post;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface PostService {
    List<Post> getAllPosts(List<UserInfo> userInfos);

    List<Post> getPostByUser(UserInfo userInfo);

    Post getPostByPostId(int postId);

    boolean addPost(Post post);

    boolean addComment(Comment comment);

    boolean updateLike(int postId, int userId);
}
