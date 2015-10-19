package main.java.net.therap.facebook.dao;

import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface LikeDao {

    boolean insertIntoLike(int postId, int userId);

    boolean getLikeStatus(int postId, int userId);

    List<UserInfo> getAllLikeInfo(int postId);

    boolean deleteLike(int postId, int userId);
}
