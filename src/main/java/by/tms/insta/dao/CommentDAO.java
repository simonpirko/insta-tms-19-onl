package by.tms.insta.dao;

import by.tms.insta.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
    void save(Comment comment);
    void deleteById(int comment_id);
    void updateMessageById(int comment_id, String message);
    Optional<List<Comment>> findByPostId(int post_id);
}
