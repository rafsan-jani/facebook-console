package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.Message;
import main.java.net.therap.facebook.entities.UserInfo;
import main.java.net.therap.facebook.utils.ConnectionManager;
import main.java.net.therap.facebook.utils.UserInput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public class MessageDaoImp implements MessageDao {
    private final String OUTBOX = "SELECT * FROM message WHERE sender_id=? ORDER BY message_id DESC";
    private final String INBOX = "SELECT * FROM message WHERE receiver_id=? ORDER BY message_id DESC";
    private final String INSERT_MSG = "INSERT INTO message (sender_id,receiver_id,content) VALUES(?, ?, ?)";

    @Override
    public List<Message> getOutbox(UserInfo sender) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Message> messageList = null;
        try {
            messageList = new ArrayList<>();
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(OUTBOX);
            preparedStatement.setInt(1, sender.getUserId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int messageId = resultSet.getInt(1);
                int senderId = resultSet.getInt(2);
                int receiverId = resultSet.getInt(3);
                String content = UserInput.getInstance().getString(resultSet.getClob(4));
                Message newMessage = new Message(messageId, sender, new UserInfo(receiverId), content);
                messageList.add(newMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return messageList;
    }

    @Override
    public List<Message> getInbox(UserInfo receiver) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Message> messageList = null;
        try {
            messageList = new ArrayList<>();
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(INBOX);
            preparedStatement.setInt(1, receiver.getUserId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int messageId = resultSet.getInt(1);
                int senderId = resultSet.getInt(2);
                int receiverId = resultSet.getInt(3);
                String content = UserInput.getInstance().getString(resultSet.getClob(4));
                Message newMessage = new Message(messageId, new UserInfo(senderId), receiver, content);
                messageList.add(newMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return messageList;
    }

    @Override
    public boolean insertIntoMessage(Message message) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_MSG);
            Clob clob = connection.createClob();
            clob.setString(1, message.getContent());


            preparedStatement.setInt(1, message.getSender().getUserId());
            preparedStatement.setInt(2, message.getReceiver().getUserId());
            preparedStatement.setClob(3, clob);

            preparedStatement.executeUpdate();
            connection.commit();

            return true;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return false;
    }
}
