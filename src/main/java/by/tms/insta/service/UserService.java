package by.tms.insta.service;

import by.tms.insta.dao.JDBCUserDAO;
import by.tms.insta.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService instance;

    private final JDBCUserDAO JDBCUserDAO = by.tms.insta.dao.JDBCUserDAO.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {

    }

    public void save(User user) {
        try {
            JDBCUserDAO.save(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int user_id) {
        JDBCUserDAO.deleteById(user_id);
    }

    public List<User> findAll() {
        return JDBCUserDAO.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return JDBCUserDAO.findByUsername(username);
    }

    public Optional<User> findByUserId(int user_id) {
        return JDBCUserDAO.findByUserId(user_id);
    }

    public List<User> extractFollowers(int user_id) {
        return JDBCUserDAO.extractFollowers(user_id);
    }

    public List<User> extractFollowed(int user_id) {
        return JDBCUserDAO.extractFollowed(user_id);
    }

    public void extractCountOfFollowers(int user_id) {
        JDBCUserDAO.extractCountOfFollowers(user_id);
    }

    public void extractCountOfFollowed(int user_id) {
        JDBCUserDAO.extractCountOfFollowed(user_id);
    }
}
