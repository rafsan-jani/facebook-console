package main.java.net.therap.facebook.services;

import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface FriendListService {
    List<UserInfo> getFriendList(UserInfo userInfo);

    List<UserInfo> getNotYetFriendList(UserInfo userInfo);

    boolean makeFriend(int userId, int friendId);
}
