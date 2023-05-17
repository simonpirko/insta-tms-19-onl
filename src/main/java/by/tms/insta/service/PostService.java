package by.tms.insta.service;

import by.tms.insta.dao.JDBCPostDAO;
import by.tms.insta.dao.PostDAO;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public void createPost(Post post) throws SQLException {
        jdbcPostDAO.save(post);

    }

    public Optional<Post> findPostById(int id) throws SQLException {
        return jdbcPostDAO.findPostById(id);
    }

    public void updatePost(Post post) throws SQLException {
        jdbcPostDAO.updatePost(post.getId(), post.getImage(), post.getDescription());
    }

    public void deletePost(int id) throws SQLException {
        jdbcPostDAO.deletePost(id);
    }

    public List<Post> getPostsByUser(int userId) throws SQLException {
        return jdbcPostDAO.getPostsByUserId(userId);
    }
    public List<Post> getFollowedUsersPosts(int userId, int limit, int offset) throws SQLException {
       return jdbcPostDAO.getFollowedUsersPosts(userId, limit, offset);
    }

    public int getCountOfPagesWithPosts(User user, int postsPerPage) throws SQLException {
        int countOfAllPosts = jdbcPostDAO.getCountByUser(user.getId());
        return (int) Math.ceil(countOfAllPosts / postsPerPage);
    }

    public List<Post> getPostsByUserWithOffset(User user, int limit, int offset) throws SQLException {
        return jdbcPostDAO.getPostsByUserWithOffset(user.getId(), limit, offset);
    }
}
