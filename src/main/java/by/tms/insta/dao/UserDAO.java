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

    void deleteById(long user_id) throws IOException;

    List<User> findAll() throws IOException;

    Optional<User> findByUsername(String username) throws IOException;

    Optional<User> findByUserId(long user_id) throws IOException;

    List<User> extractFollowers(long user_id) throws IOException;

    List<User> extractFollowed(long user_id) throws IOException;

    void extractCountOfFollowers(long user_id) throws IOException;

    void extractCountOfFollowed(long user_id) throws IOException;

}
