package by.tms.insta.service;

import by.tms.insta.dao.CommentDAO;
import by.tms.insta.dao.JDBCCommentDAO;
import by.tms.insta.entity.Comment;

import java.sql.SQLException;
import java.util.List;


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

    public void save(Comment comment) throws SQLException {
        jdbcCommentDAO.save(comment);
    }

    public void deleteById(int commentId) throws SQLException {
        jdbcCommentDAO.deleteById(commentId);
    }

    public void updateMessageById(int commentId, String message) throws SQLException {
        jdbcCommentDAO.updateMessageById(commentId, message);
    }

    public List<Comment> findByPostId(int postId, int paginationOffset, int limit) throws SQLException {
        return jdbcCommentDAO.findByPostId(postId, paginationOffset, limit);
    }
    public int getCountOfPages(int postId,int commentPerPage){
        int countByPostId = jdbcCommentDAO.getCountByPostId(postId);
        return (int) Math.ceil(countByPostId / commentPerPage);
    }
}
