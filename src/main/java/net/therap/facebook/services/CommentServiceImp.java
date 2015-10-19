package main.java.net.therap.facebook.services;

import main.java.net.therap.facebook.dao.CommentDaoImp;
import main.java.net.therap.facebook.entities.Comment;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class CommentServiceImp implements CommentService {
    CommentDaoImp commentDaoImp;
    PersonalInfoServiceImp personalInfoServiceImp;

    public CommentServiceImp() {
        commentDaoImp = new CommentDaoImp();
        personalInfoServiceImp = new PersonalInfoServiceImp();
    }

    @Override
    public List<Comment> getAllComments(int postId) {
        List<Comment> comments = commentDaoImp.getAllComments(postId);
        for (int index = 0; index < comments.size(); index++) {
            Comment comment = comments.get(index);
            comment.setUserInfo(personalInfoServiceImp.getPersonalDetails(comment.getUserInfo().getUserId()));
            comments.set(index, comment);
        }
        return comments;
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentDaoImp.insertIntoComment(comment);
    }
}
