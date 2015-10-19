package main.java.net.therap.facebook.services;

import main.java.net.therap.facebook.entities.Comment;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface CommentService {
    List<Comment> getAllComments(int postId);

    boolean addComment(Comment comment);
}
