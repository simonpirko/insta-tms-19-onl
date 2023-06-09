package by.tms.insta.dao;

import by.tms.insta.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAO {
    void save(Comment comment) throws SQLException;
    void deleteById(int commentId) throws SQLException;
    void updateMessageById(int commentId, String message) throws SQLException;
    List<Comment> findByPostId(int postId, int paginationOffset, int limit) throws SQLException;
    void deleteAllByPostId(int postId) throws SQLException;
    int getCountByPostId(int postId);
}
