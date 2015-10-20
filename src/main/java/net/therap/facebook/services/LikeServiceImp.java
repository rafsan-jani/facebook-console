package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.dao.LikeDaoImp;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class LikeServiceImp implements LikeService {

    LikeDaoImp likeDaoImp;

    public LikeServiceImp() {
        likeDaoImp = new LikeDaoImp();
    }

    @Override
    public List<UserInfo> getAllLikes(int postId) {
        return likeDaoImp.getAllLikeInfo(postId);
    }

    @Override
    public boolean isLiked(int postId, int userId) {
        return likeDaoImp.getLikeStatus(postId, userId);
    }

    @Override
    public boolean deleteLike(int postId, int userId) {
        return likeDaoImp.deleteLike(postId, userId);
    }

    @Override
    public boolean addLike(int postId, int userId) {
        return likeDaoImp.insertIntoLike(postId, userId);
    }
}
