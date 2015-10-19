package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.UserInfo;
import main.java.net.therap.facebook.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class FriendListDaoImp implements FriendListDao {

    @Override
    public List<UserInfo> getFriendList(int userId) {
        List<UserInfo> userInfos = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM personal_info WHERE user_id IN (SELECT user_id FROM friend_list WHERE friend_id=?)");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            userInfos = new ArrayList<UserInfo>();
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String sex = resultSet.getString(4);
                Date dob = resultSet.getDate(5);
                int age = resultSet.getInt(6);
                UserInfo userInfo = new UserInfo(id, firstName, lastName, sex, dob, age);
                userInfos.add(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return userInfos;
    }

    @Override
    public boolean makeFriend(int userId, int friendId) {
        if (isFriend(userId, friendId) == false) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                StringBuffer sql = new StringBuffer("INSERT INTO friend_list (user_id, friend_id) " +
                        "VALUES(?, ?)");
                connection = ConnectionManager.getConnection();
                connection.setAutoCommit(false);

                preparedStatement = connection.prepareStatement(sql.toString());
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, friendId);

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
        } else {
            System.out.println("Already in friend list!!!");
        }
        return false;
    }

    private boolean isFriend(int userId, int friendId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM friend_list WHERE user_id=? and friend_id=?");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return false;
    }

    @Override
    public List<UserInfo> getNotYetFriendList(UserInfo userInfo) {
        List<UserInfo> userInfos = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int userId = userInfo.getUserId();
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM personal_info WHERE user_id NOT IN (SELECT user_id FROM friend_list WHERE friend_id=?)and user_id!=?");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            userInfos = new ArrayList<UserInfo>();
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int friendId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String sex = resultSet.getString(4);
                Date dob = resultSet.getDate(5);
                int age = resultSet.getInt(6);
                UserInfo newUser = new UserInfo(friendId, firstName, lastName, sex, dob, age);
                userInfos.add(newUser);
            }
            return userInfos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return null;
    }
}
