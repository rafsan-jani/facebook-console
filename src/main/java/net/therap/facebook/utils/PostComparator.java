package main.java.net.therap.facebook.utils;


import main.java.net.therap.facebook.entities.Post;

import java.util.Comparator;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class PostComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o2.getTime().compareTo(o1.getTime());
    }
}
