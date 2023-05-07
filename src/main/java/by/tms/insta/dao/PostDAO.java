package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PostDAO {

    void createPost(Post post);

    Post findPostById (int id);

    void updatePost(Post post, int postId);

    void deletePost(int id);

    List<Post> getPostsByUser(User user);

    List<Comment> getCommentsByPost(Post post);

}