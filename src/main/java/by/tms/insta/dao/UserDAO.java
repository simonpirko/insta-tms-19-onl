package by.tms.insta.dao;

import by.tms.insta.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 28.04.2023
 */

public interface UserDAO {

    void save(User user) throws IOException;

    void deleteById(int userId) throws IOException;

    List<User> findAll() throws IOException;

    Optional<User> findByUsername(String username) throws IOException;

    Optional<User> findByUserId(int userId) throws IOException;

    List<User> extractFollowers(int userId) throws IOException;

    List<User> extractFollowed(int userId) throws IOException;

    void extractCountOfFollowers(int userId) throws IOException;

    void extractCountOfFollowed(int userId) throws IOException;

}
