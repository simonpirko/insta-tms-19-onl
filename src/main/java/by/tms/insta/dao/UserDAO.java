package by.tms.insta.dao;

import by.tms.insta.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 28.04.2023
 */

public interface UserDAO {

    void save(User user);

    void deleteById(int userId);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findByUserId(int userId);

    List<User> extractFollowers(int userId);

    List<User> extractFollowed(int userId);

    int extractCountOfFollowers(int userId);

    int extractCountOfFollowed(int userId);

    void follow(int parentId, int childId);

    void unfollow(int parentId, int childId);

}
