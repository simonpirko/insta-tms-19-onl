package by.tms.insta.dao;

import by.tms.insta.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 28.04.2023
 */

public interface UserDAO {

    void save(User user) throws SQLException;

    void deleteById(int userId) throws SQLException;

    List<User> findAll() throws SQLException;

    Optional<User> findByUsername(String username) throws SQLException;

    Optional<User> findByUserId(int userId) throws SQLException;

    List<User> extractFollowers(int userId) throws SQLException;

    List<User> extractFollowed(int userId) throws SQLException;

    int extractCountOfFollowers(int userId) throws SQLException;

    int extractCountOfFollowed(int userId) throws SQLException;

    void follow(int parentId, int childId) throws SQLException;

    void unfollow(int parentId, int childId) throws SQLException;

    void update(int userId, String name, String email, String avatar);

    void updateWithPassword(int userId, String name, String email, String avatar, String password);

}
