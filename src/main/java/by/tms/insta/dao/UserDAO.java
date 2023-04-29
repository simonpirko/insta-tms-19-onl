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

    void deleteById(int user_id) throws IOException;

    List<User> findAll() throws IOException;

    Optional<User> findByUsername(String username) throws IOException;

    Optional<User> findByUserId(int user_id) throws IOException;

    List<User> extractFollowers(int user_id) throws IOException;

    List<User> extractFollowed(int user_id) throws IOException;

    void extractCountOfFollowers(int user_id) throws IOException;

    void extractCountOfFollowed(int user_id) throws IOException;

}
