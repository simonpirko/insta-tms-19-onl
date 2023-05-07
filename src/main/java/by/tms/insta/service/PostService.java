package by.tms.insta.service;

import by.tms.insta.dao.JDBCPostDAO;
import by.tms.insta.dao.PostDAO;
import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.util.List;

/**
 * @author Denis Smirnov on 03.05.2023
 */
public class PostService {

    private static PostService instance;

    private final PostDAO jdbcPostDAO = JDBCPostDAO.getInstance();

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    private PostService() {

    }

    public void createPost(Post post) {
        jdbcPostDAO.createPost(post);

    }

    public Post findPostById(int id) {
        return jdbcPostDAO.findPostById(id);
    }

    public void updatePost(Post post, int postId) {
        jdbcPostDAO.updatePost(post, postId);
    }

    public void deletePost(int id) {
        jdbcPostDAO.deletePost(id);
    }

    public List<Post> getPostsByUser(User user) {
        return jdbcPostDAO.getPostsByUser(user);
    }

    public List<Comment> getCommentsByPost(Post post) {
        return jdbcPostDAO.getCommentsByPost(post);
    }
}
