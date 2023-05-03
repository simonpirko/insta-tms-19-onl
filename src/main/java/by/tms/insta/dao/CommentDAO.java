package by.tms.insta.dao;

import by.tms.insta.entity.Comment;

import java.util.List;

public interface CommentDAO {
    void save(Comment comment);
    void deleteById(int commentId);
    void updateMessageById(int commentId, String message);
    List<Comment> findByPostId(int postId, int paginationOffset);
}
