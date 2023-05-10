package by.tms.insta.service;

import by.tms.insta.dao.JDBCUserDAO;
import by.tms.insta.entity.User;

import java.io.IOException;
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

    public void save(User user) { jdbcUserDAO.save(user); }

    public void deleteById(int userId) {
        jdbcUserDAO.deleteById(userId);
    }

    public List<User> findAll() {
        return jdbcUserDAO.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return jdbcUserDAO.findByUsername(username);
    }

    public boolean checkUsername(String username) {
        Optional<User> optional = jdbcUserDAO.findByUsername(username);
        return optional.isPresent();
    }

    public Optional<User> findByUserId(int userId) {
        return jdbcUserDAO.findByUserId(userId);
    }

    public List<User> extractFollowers(int userId) {
        return jdbcUserDAO.extractFollowers(userId);
    }

    public List<User> extractFollowed(int userId) {
        return jdbcUserDAO.extractFollowed(userId);
    }

    public int extractCountOfFollowers(int userId) {
        return jdbcUserDAO.extractCountOfFollowers(userId);
    }

    public int extractCountOfFollowed(int userId) {
        return jdbcUserDAO.extractCountOfFollowed(userId);
    }

    public void follow (int parentId, int childId) {jdbcUserDAO.follow(parentId, childId);}

    public void unFollow (int parentId, int childId) {jdbcUserDAO.unfollow(parentId, childId);}
}
