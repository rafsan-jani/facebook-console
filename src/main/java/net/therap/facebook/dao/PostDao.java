package main.java.net.therap.facebook.dao;

import main.java.net.therap.facebook.entities.Post;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface PostDao {

    boolean insertIntoPost(Post post);

    List<Post> getPostByUserId(UserInfo userInfo);

    Post getPostByPostId(int postId);
}
