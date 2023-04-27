package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CommentDao {
    private static CommentDao instance;
    Connection connection;
    private static final String WRITE_COMMENT = "insert into comments (message, post_id, user_id, created_at) value(?,?,?,?)";
    private static final String DELETE_COMMENT = "delete from comments where comment_id = ?";
    private static final String UPDATE_COMMENT = "update comments set message = ? where comment_id = ?";
    private static final String SELECT_BY_POST_ID = "select * from comments where post_id = ?";

    private CommentDao() {
    }

    public CommentDao getInstance() {
        if (instance == null) {
            instance = new CommentDao();
        }
        return instance;
    }

    public void save(Comment comment) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(WRITE_COMMENT);
            preparedStatement.setString(1, comment.getMessage());
            preparedStatement.setLong(2, comment.getPost().getId());
            preparedStatement.setLong(3, comment.getAuthor().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int comment_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT);
            preparedStatement.setInt(1, comment_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMessageById(int comment_id, String message) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT);
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, comment_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findByPostId(int post_id) {
        try {
            List<Comment> commentList = new ArrayList<Comment>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_POST_ID);
            preparedStatement.setInt(1, post_id);
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                int comment_id = resultSet.getInt("comment_id");
                String message = resultSet.getString("message");
                int user_id = resultSet.getInt("user_id");
                LocalDateTime time = resultSet.getTimestamp("created_at").toLocalDateTime();
                Comment.CommentBuilder builder = Comment.builder();
                Comment comment = builder.setId(comment_id)
                        .setMessage(message)
                        .setAuthor()
                        .setPost()
                        .setCreateAt(time)
                        .build();
                commentList.add(comment);
            }
            Collections.sort(commentList);
            return commentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
