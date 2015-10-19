package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.UserInfo;
import main.java.net.therap.facebook.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public class PersonalInfoDaoImp implements PersonalInfoDao {

    @Override
    public UserInfo getPersonalInfo(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM personal_info WHERE user_id=?");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            String firstName;
            String lastName;
            String sex;
            Date dateOfBirth = null;
            int age;
            if (resultSet.next()) {
                firstName = resultSet.getString(2);
                lastName = resultSet.getString(3);
                sex = resultSet.getString(4);
                dateOfBirth = resultSet.getDate(5);//"date_of_birth"
                age = resultSet.getInt(6);
                return new UserInfo(userId, firstName, lastName, sex, dateOfBirth, age);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return null;
    }

    @Override
    public boolean updatePersonalInfo(UserInfo userInfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            StringBuffer sql = new StringBuffer("UPDATE personal_info SET " +
                    "first_name=?, last_name=?,sex=?,date_of_birth=?,age=? " +
                    "WHERE user_id=?");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1, userInfo.getFirstName());
            preparedStatement.setString(2, userInfo.getLastName());
            preparedStatement.setString(3, userInfo.getSex());
            preparedStatement.setDate(4, new java.sql.Date(userInfo.getDateOfBirth().getTime()));
            preparedStatement.setInt(5, userInfo.getAge());
            preparedStatement.setInt(6, userInfo.getUserId());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return false;
    }

    @Override
    public boolean insertPersonalInfo(UserInfo userInfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO personal_info (user_id, first_name,last_name,sex, date_of_birth,age) " +
                    "VALUES(?, ?, ?, ?, ?, ?)");
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, userInfo.getUserId());
            preparedStatement.setString(2, userInfo.getFirstName());
            preparedStatement.setString(3, userInfo.getLastName());
            preparedStatement.setString(4, userInfo.getSex());
            preparedStatement.setDate(5, new java.sql.Date(userInfo.getDateOfBirth().getTime()));
            preparedStatement.setInt(6, userInfo.getAge());
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
