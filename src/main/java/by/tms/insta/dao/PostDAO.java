package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PostDAO {

    void createPost(Post post) throws IOException;

    Post findPostById (int id) throws IOException;

    void updatePost(Post post, int postId) throws IOException;

    void deletePost(int id) throws IOException, SQLException;

    List<Post> getPostsByUser(User user) throws IOException;

    List<Comment> getCommentsByPost(Post post ) throws IOException;

}