package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.Post;
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

public class PostDaoImp implements PostDao {
    @Override
    public boolean insertIntoPost(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            StringBuffer sql = new StringBuffer("INSERT INTO post (user_id,post_content,post_time) " +
                    "VALUES(?, ?, ?)");

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql.toString());

            Clob clob = connection.createClob();
            clob.setString(1, post.getContent());

            preparedStatement.setInt(1, post.getUserInfo().getUserId());
            preparedStatement.setClob(2, clob);
            preparedStatement.setDate(3, new java.sql.Date(post.getTime().getTime()));
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
    public List<Post> getPostByUserId(UserInfo userInfo) {
        List<Post> posts = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM post WHERE user_id= ? " +
                    "ORDER BY post_time DESC");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, userInfo.getUserId());
            resultSet = preparedStatement.executeQuery();

            posts = new ArrayList<Post>();
            while (resultSet.next()) {
                int postId = resultSet.getInt(1);
                Clob clob = resultSet.getClob(3);
                String content = UserInput.getInstance().getString(clob);
                Date time = resultSet.getDate(4);
                Post post = new Post(postId, userInfo, content, time);
                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return posts;
    }


    @Override
    public Post getPostByPostId(int postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Post post = null;
        try {
            StringBuffer sql = new StringBuffer("SELECT * FROM post WHERE post_id= ? ");
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());

            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(2);
                Clob clob = resultSet.getClob(3);
                String content = UserInput.getInstance().getString(clob);
                Date time = resultSet.getDate(4);
                post = new Post(postId, new UserInfo(userId), content, time);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return post;
    }

}
