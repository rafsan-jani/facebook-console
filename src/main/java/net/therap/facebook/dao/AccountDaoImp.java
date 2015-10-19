package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public class AccountDaoImp implements AccountDao {

    private static String EMAIL_QUERY = "SELECT user_id FROM account WHERE email= ?";

    @Override
    public int getUserId(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT user_id FROM account WHERE email= ? and password = ?");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return -1;
    }

    @Override
    public boolean isUsedEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(EMAIL_QUERY);
            preparedStatement.setString(1, email);
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
    public boolean insertIntoAccount(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO account (email,password) " +
                    "VALUES(?, ?)");
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
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

