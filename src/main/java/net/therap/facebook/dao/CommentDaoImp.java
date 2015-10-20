package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.Comment;
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
public class CommentDaoImp implements CommentDao {
    private final String INSERT_COMMENT = "INSERT INTO comment (post_id,user_id,content,comment_time) VALUES(?, ?, ?, ?)";
    private final String GET_COMMENTS = "SELECT * FROM comment WHERE post_id= ? ORDER BY comment_time DESC";
    @Override
    public List<Comment> getAllComments(int postId) {
        List<Comment> comments = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(GET_COMMENTS);

            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();

            comments = new ArrayList<>();
            while (resultSet.next()) {
                int commentId = resultSet.getInt(1);
                int npostId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                Clob clob = resultSet.getClob(4);
                String content = UserInput.getInstance().getString(clob);
                Date time = resultSet.getDate(5);
                Comment comment = new Comment(commentId, postId, new UserInfo(userId), content, time);
                comments.add(comment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(resultSet);
            ConnectionManager.close(preparedStatement);
            ConnectionManager.close(connection);
        }
        return comments;
    }

    @Override
    public boolean insertIntoComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {


            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_COMMENT);
            Clob clob = connection.createClob();
            clob.setString(1, comment.getContent());

            preparedStatement.setInt(1, comment.getPostId());
            preparedStatement.setInt(2, comment.getUserInfo().getUserId());
            preparedStatement.setClob(3, clob);
            preparedStatement.setDate(4, new java.sql.Date(comment.getTime().getTime()));
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
