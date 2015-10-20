package main.java.net.therap.facebook.services;

import main.java.net.therap.facebook.entities.Message;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface MessageService {

    List<Message> getOutbox(UserInfo userInfo);

    List<Message> getInbox(UserInfo userInfo);

    boolean sendMessage(Message message);
}
