package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.dao.PostDaoImp;
import main.java.net.therap.facebook.entities.Comment;
import main.java.net.therap.facebook.entities.Post;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class PostServiceImp implements PostService {
    private PostDaoImp postDaoImp;
    private CommentServiceImp commentServiceImp;
    private LikeServiceImp likeServiceImp;
    private PersonalInfoServiceImp personalInfoServiceImp;

    public PostServiceImp() {
        postDaoImp = new PostDaoImp();
        commentServiceImp = new CommentServiceImp();
        likeServiceImp = new LikeServiceImp();
        personalInfoServiceImp = new PersonalInfoServiceImp();
    }

    @Override
    public List<Post> getAllPosts(List<UserInfo> userInfos) {
        List<Post> allPosts = new ArrayList<>();
        List<Post> currentPosts = null;
        for (UserInfo user : userInfos) {
            currentPosts = postDaoImp.getPostByUserId(user);
            for (Post post : currentPosts) {
                allPosts.add(post);
            }
        }
        return allPosts;
    }

    @Override
    public List<Post> getPostByUser(UserInfo userInfo) {
        return postDaoImp.getPostByUserId(userInfo);
    }

    @Override
    public Post getPostByPostId(int postId) {
        Post post = postDaoImp.getPostByPostId(postId);
        post.setUserInfo(personalInfoServiceImp.getPersonalDetails(post.getUserInfo().getUserId()));
        post.setComments(commentServiceImp.getAllComments(post.getPostId()));
        post.setLikes(likeServiceImp.getAllLikes(post.getPostId()));
        return post;
    }

    @Override
    public boolean addPost(Post post) {
        return postDaoImp.insertIntoPost(post);
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentServiceImp.addComment(comment);
    }

    @Override
    public boolean updateLike(int postId, int userId) {
        boolean status = false;
        if (likeServiceImp.isLiked(postId, userId)) {
            status = likeServiceImp.deleteLike(postId, userId);
        } else {
            status = likeServiceImp.addLike(postId, userId);
        }
        return status;
    }
}
