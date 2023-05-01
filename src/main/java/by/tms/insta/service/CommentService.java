package by.tms.insta.service;

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

    private final CommentDAO JDBCCommentDAO = JDBCCommentDAO.getInstance();

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    private CommentService() {

    }

    public void save(Comment comment) {
        try {
            JDBCCommentDAO.save(comment);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int comment_id) {
        JDBCCommentDAO.deleteById(comment_id);
    }

    public void updateMessageById(int comment_id, String message) {
        JDBCCommentDAO.updateMessageById(comment_id, message);
    }

    public Optional<List<Comment>> findByPostId(int post_id) {
        return JDBCCommentDAO.findByPostId(post_id);
    }
}
