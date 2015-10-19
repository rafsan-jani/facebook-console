package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.Message;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface MessageDao {

    List<Message> getOutbox(UserInfo userInfo);

    List<Message> getInbox(UserInfo userInfo);

    boolean insertIntoMessage(Message message);
}
