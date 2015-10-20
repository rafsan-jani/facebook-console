package main.java.net.therap.facebook.dao;

import main.java.net.therap.facebook.entities.Comment;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public interface CommentDao {

    List<Comment> getAllComments(int postId);

    boolean insertIntoComment(Comment comment);
}
