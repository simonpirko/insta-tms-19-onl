package by.tms.insta.dao;

import javax.xml.stream.events.Comment;
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
    public List<Comment> findByPostId(int post_id){
        try {
            List<Comment> commentList = new ArrayList<Comment>();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_POST_ID);
            preparedStatement.setInt(1, post_id);
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                int comment_id = resultSet.getInt(1);
                String string = resultSet.getString(2);
                int user_id = resultSet.getInt(4);
                LocalDateTime time = resultSet.getTimestamp(5).toLocalDateTime();
                Comment comment = new Comment();
                commentList.add(comment);
            }
            Collections.sort(commentList);
            return commentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
