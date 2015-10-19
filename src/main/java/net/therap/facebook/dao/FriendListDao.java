package main.java.net.therap.facebook.dao;

import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public interface FriendListDao {

    List<UserInfo> getFriendList(int userId);

    boolean makeFriend(int userId, int friendId);

    List<UserInfo> getNotYetFriendList(UserInfo userInfo);
}
