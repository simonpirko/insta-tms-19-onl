package by.tms.insta.web.service;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
        private static UserService instance;
        private final UserStorage storage = JdbcUserStorage.getInstance();
        private UserService() throws SQLException {

        }
        public static UserService getInstance() throws SQLException {
            if (instance == null){
                instance = new UserService();
            }
            return instance;
        }

    public void save(User user) {
        storage.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return storage.findByUsername(username);
    }

    public boolean checkUsername(String username) {
        return storage.checkUsername(username);
    }
}
