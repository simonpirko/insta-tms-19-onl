package by.tms.insta.service;

import by.tms.insta.dao.JDBCUserDAO;
import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.User;
import by.tms.insta.validation.UserValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;

    private final JDBCUserDAO jdbcUserDAO = JDBCUserDAO.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {

    }

    public void save(User user) throws SQLException {
        jdbcUserDAO.save(user);
    }

    public void deleteById(int userId) throws SQLException {
        jdbcUserDAO.deleteById(userId);
    }

    public List<User> findAll() throws SQLException {
        return jdbcUserDAO.findAll();
    }

    public Optional<User> findByUsername(String username) throws SQLException {
        return jdbcUserDAO.findByUsername(username);
    }

    public boolean checkUsername(String username) throws SQLException {
        Optional<User> optional = jdbcUserDAO.findByUsername(username);
        return optional.isPresent();
    }

    public Optional<User> findByUserId(int userId) throws SQLException {
        return jdbcUserDAO.findByUserId(userId);
    }

    public List<User> extractFollowers(int userId) throws SQLException {
        return jdbcUserDAO.extractFollowers(userId);
    }

    public List<User> extractFollowed(int userId) throws SQLException {
        return jdbcUserDAO.extractFollowed(userId);
    }

    public int extractCountOfFollowers(int userId) throws SQLException {
        return jdbcUserDAO.extractCountOfFollowers(userId);
    }

    public int extractCountOfFollowed(int userId) throws SQLException {
        return jdbcUserDAO.extractCountOfFollowed(userId);
    }

    public void follow(int parentId, int childId) throws SQLException {
        jdbcUserDAO.follow(parentId, childId);
    }

    public void unFollow(int parentId, int childId) throws SQLException {
        jdbcUserDAO.unfollow(parentId, childId);
    }

    public boolean isFollower(String followedUsername, String followerUsername){
        int countOfItems = jdbcUserDAO.isFollower(followedUsername, followerUsername);
        return countOfItems != 0;
      
    public void updateUserProfile(User oldUser, User newUser) {
        String name = newUser.getName().isEmpty() ? oldUser.getName() : newUser.getName();
        String email = newUser.getEmail().isEmpty() ? oldUser.getEmail() : newUser.getEmail();
        String avatar = newUser.getAvatar().isEmpty() ? oldUser.getAvatar() : newUser.getAvatar();

        if (newUser.getPassword().isEmpty()) {
            jdbcUserDAO.update(newUser.getId(), name, email, avatar);
        } else {
            jdbcUserDAO.updateWithPassword(newUser.getId(), name, email, avatar, newUser.getPassword());
        }
    }
}
