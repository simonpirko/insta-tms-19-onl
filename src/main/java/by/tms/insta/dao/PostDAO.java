package by.tms.insta.dao;

import by.tms.insta.entity.Post;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostDAO {

    void save(Post post) throws SQLException;

    Optional<Post> findPostById (int id) throws SQLException;

    void updatePost(int postId, String image, String description, LocalDateTime createdAt) throws SQLException;

    void deletePost(int id) throws SQLException;

    List<Post> getPostsByUserId(int userId) throws SQLException;
    void Like(int user_id, int post_id) throws SQLException;
    void unLike(int user_id) throws SQLException;
    int extractCountOfLikes(int post_id) throws SQLException;

}