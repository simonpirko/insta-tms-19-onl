package by.tms.insta.dao;

import by.tms.insta.entity.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PostDAO {

    void save(Post post) throws SQLException;

    Optional<Post> findPostById (int id) throws SQLException;

    void updatePost(int postId, String image, String description) throws SQLException;

    void deletePost(int id) throws SQLException;

    List<Post> getPostsByUserId(int userId) throws SQLException;
    void like(int user_id, int post_id) throws SQLException;
    void unLike(int user_id) throws SQLException;
    int extractCountOfLikes(int post_id) throws SQLException;

    int getCountByUser(int user_id) throws SQLException;

    List<Post> getPostsByUserWithOffset(int user_id, int limit, int offset) throws SQLException;
}