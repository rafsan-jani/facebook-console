package main.java.net.therap.facebook.services;

import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface LikeService {
    List<UserInfo> getAllLikes(int postId);

    boolean isLiked(int postId, int userId);

    boolean deleteLike(int postId, int userId);

    boolean addLike(int postId, int userId);
}
