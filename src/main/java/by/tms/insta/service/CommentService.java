package by.tms.insta.service;

import by.tms.insta.dao.CommentDAO;
import by.tms.insta.dao.JDBCCommentDAO;
import by.tms.insta.entity.Comment;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 01.05.2023
 */
public class CommentService {
    private static CommentService instance;

    private final CommentDAO jdbcCommentDAO = JDBCCommentDAO.getInstance();

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    private CommentService() {

    }

    public void save(Comment comment) {
        jdbcCommentDAO.save(comment);
    }

    public void deleteById(int commentId) {
        jdbcCommentDAO.deleteById(commentId);
    }

    public void updateMessageById(int commentId, String message) {
        jdbcCommentDAO.updateMessageById(commentId, message);
    }

    public List<Comment> findByPostId(int postId, int  paginationOffset) {
        return jdbcCommentDAO.findByPostId(postId, paginationOffset);
    }
}
