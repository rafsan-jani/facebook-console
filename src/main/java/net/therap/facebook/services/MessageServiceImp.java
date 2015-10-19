package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.dao.MessageDaoImp;
import main.java.net.therap.facebook.entities.Message;
import main.java.net.therap.facebook.entities.UserInfo;

import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class MessageServiceImp implements MessageService {
    MessageDaoImp messageDaoImp;
    PersonalInfoServiceImp personalInfoServiceImp;

    public MessageServiceImp() {
        personalInfoServiceImp = new PersonalInfoServiceImp();
        messageDaoImp = new MessageDaoImp();
    }

    @Override
    public List<Message> getOutbox(UserInfo userInfo) {
        List<Message> messages = messageDaoImp.getOutbox(userInfo);
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            message.setReceiver(personalInfoServiceImp.getPersonalDetails(message.getReceiver().getUserId()));
            messages.set(i, message);
        }
        return messages;
    }

    @Override
    public List<Message> getInbox(UserInfo userInfo) {
        List<Message> messages = messageDaoImp.getInbox(userInfo);
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            message.setSender(personalInfoServiceImp.getPersonalDetails(message.getSender().getUserId()));
            messages.set(i, message);
        }
        return messages;
    }

    @Override
    public boolean sendMessage(Message message) {
        return messageDaoImp.insertIntoMessage(message);
    }
}
