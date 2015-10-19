package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.UserInfo;
import main.java.net.therap.facebook.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class LikeDaoImp implements LikeDao {

    @Override
    public boolean insertIntoLike(int postId, int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO likes (post_id,user_id) " +
                    "VALUES(?, ?)");

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
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

    @Override
    public boolean getLikeStatus(int postId, int userId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM likes WHERE " +
                    "post_id= ? and user_id = ?");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return false;
    }

    @Override
    public List<UserInfo> getAllLikeInfo(int postId) {
        List<UserInfo> userInfos = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM personal_info WHERE user_id IN (SELECT user_id FROM likes WHERE post_id=?)");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            userInfos = new ArrayList<UserInfo>();

            preparedStatement.setInt(1, postId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String sex = resultSet.getString(4);
                java.sql.Date dob = resultSet.getDate(5);
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
    public boolean deleteLike(int postId, int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            StringBuffer sql = new StringBuffer("DELETE FROM likes WHERE post_id=? and user_id=?");

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
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
