package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.dao.FriendListDaoImp;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class FriendListServiceImp implements FriendListService {
    private FriendListDaoImp friendListDaoImp;

    public FriendListServiceImp() {
        friendListDaoImp = new FriendListDaoImp();
    }

    @Override
    public List<UserInfo> getFriendList(UserInfo userInfo) {
        return friendListDaoImp.getFriendList(userInfo.getUserId());
    }

    @Override
    public List<UserInfo> getNotYetFriendList(UserInfo userInfo) {
        return friendListDaoImp.getNotYetFriendList(userInfo);
    }

    @Override
    public boolean makeFriend(int userId, int friendId) {
        return friendListDaoImp.makeFriend(userId, friendId) & friendListDaoImp.makeFriend(friendId, userId);
    }
}
