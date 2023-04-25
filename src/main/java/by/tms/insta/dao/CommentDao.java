package by.tms.insta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDao {
    private static CommentDao instance;
    Connection connection;
    private static final String WRITE_COMMENT = "insert into comments (message, post_id, user_id, created_at) value(?,?,?,?)";
    private static final String DELETE_COMMENT = "delete from comments where comment_id = ?";
    private static final String UPDATE_COMMENT = "update comments set message = ? where comment_id = ?";
    private CommentDao() {
    }
    public CommentDao getInstance(){
        if (instance == null){
            instance = new CommentDao();
        }
        return instance;
    }
    public void save(Comment comment){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(WRITE_COMMENT);
            preparedStatement.setString(1, comment.getMessage);
            preparedStatement.setInt(2, comment.getPost.getId);
            preparedStatement.setInt(3, comment.getUser.getId);
            preparedStatement.setTimestamp(4, comment.getCreatedAt);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(int comment_id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT);
            preparedStatement.setInt(1, comment_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateMessageById(int comment_id, String message){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT);
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, comment_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
