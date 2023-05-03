package by.tms.insta.web.service;

import by.tms.insta.dao.JDBCUserDAO;
import by.tms.insta.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {
        private static UserService instance;
        private final JDBCUserDAO storage = JDBCUserDAO.getInstance();
        private UserService() throws SQLException {

        }
        public static UserService getInstance() throws SQLException {
            if (instance == null){
                instance = new UserService();
            }
            return instance;
        }

    public void save(User user) throws IOException {
        storage.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return storage.findByUsername(username);
    }

    public boolean checkUsername(String username) {
        Optional<User> optional = storage.findByUsername(username);
        return optional.isPresent();
    }
}
