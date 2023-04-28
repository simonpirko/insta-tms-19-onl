package by.tms.insta.dao;

import by.tms.insta.entity.Comment;

import java.util.List;

public interface CommentDao {
    void save(Comment comment);
    void deleteById(int comment_id);
    void updateMessageById(int comment_id, String message);
    List<Comment> findByPostId(int post_id);
}
