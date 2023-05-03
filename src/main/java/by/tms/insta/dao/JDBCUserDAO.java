package by.tms.insta.dao;

import by.tms.insta.entity.User;
import by.tms.insta.util.ConnectionJdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 28.04.2023
 */
public class JDBCUserDAO implements UserDAO {

    private final ConnectionJdbc connectionJdbc = ConnectionJdbc.getInstance();
    private static final String INSERT_USER = "insert into users(username, password, name, photo, email) values (?, ?, ?, ?, ?)";
    private static final String DELETE_USER = "delete from users where user_id = ?";
    private static final String EXTRACT_ALL_USERS = "select * from users";
    private static final String EXTRACT_CURRENT_USER = "select * from users where username = ?";
    private static final String EXTRACT_USER_BY_ID = "select * from users where user_id = ?";
    private static final String EXTRACT_USER_FOLLOWERS = "select u.name, u.username, u.email, u.photo from followers f join users u on f.child_id = u.user_id where f.parent_id = ?";
    private static final String EXTRACT_USER_FOLLOWED = "select u.name, u.username, u.email, u.photo from followers f join users u on f.parent_id = u.user_id where f.child_id = ?";
    private static final String EXTRACT_COUNT_OF_USERS_FOLLOWERS = "select count (*) from followers where parent_id = ? ";
    private static final String EXTRACT_COUNT_OF_USER_FOLLOWED = "select count (*) from followers where child_id = ?";

    private static JDBCUserDAO instance;

    public static JDBCUserDAO getInstance() {
        if (instance == null) {
            instance = new JDBCUserDAO();
        }
        return instance;
    }

    private JDBCUserDAO() {

    }
    @Override
    public void save(User user) throws IOException {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getAvatar());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int userId) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connectionJdbc.getPostgresConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(EXTRACT_ALL_USERS);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String photo = resultSet.getString(5);
                String email = resultSet.getString(6);
                User user = User.builder()
                        .setId(id)
                        .setUsername(username)
                        .setPassword(password)
                        .setName(name)
                        .setAvatar(photo)
                        .setEmail(email)
                        .build();
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_CURRENT_USER);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String photo = resultSet.getString(5);
                String email = resultSet.getString(6);
                User user = User.builder()
                        .setId(id)
                        .setUsername(userName)
                        .setPassword(password)
                        .setName(name)
                        .setAvatar(photo)
                        .setEmail(email)
                        .build();
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<User> findByUserId(int userId) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_USER_BY_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String photo = resultSet.getString(5);
                String email = resultSet.getString(6);
                User user = User.builder()
                        .setId(id)
                        .setUsername(username)
                        .setPassword(password)
                        .setName(name)
                        .setAvatar(photo)
                        .setEmail(email)
                        .build();
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> extractFollowers(int userId) {
        try {
            List<User> followersList = new ArrayList<>();
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_USER_FOLLOWERS);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String username = resultSet.getString(2);
                String email = resultSet.getString(3);
                String photo = resultSet.getString(4);
                User user = User.builder()
                        .setName(name)
                        .setUsername(username)
                        .setEmail(email)
                        .setAvatar(photo)
                        .build();
                followersList.add(user);
            }
            return followersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> extractFollowed(int userId) {
        try {
            List<User> followedList = new ArrayList<>();
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_USER_FOLLOWED);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String username = resultSet.getString(2);
                String email = resultSet.getString(3);
                String photo = resultSet.getString(4);
                User user = User.builder()
                        .setName(name)
                        .setUsername(username)
                        .setEmail(email)
                        .setAvatar(photo)
                        .build();
                followedList.add(user);
            }
            return followedList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void extractCountOfFollowers(int userId) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_COUNT_OF_USERS_FOLLOWERS);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            if (resultSet.next()) ;
            count = resultSet.getInt(1);
            System.out.println(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void extractCountOfFollowed(int userId) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_COUNT_OF_USER_FOLLOWED);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            if (resultSet.next()) ;
            count = resultSet.getInt(1);
            System.out.println(count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}