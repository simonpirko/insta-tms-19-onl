package by.tms.insta.dao;

import by.tms.insta.entity.User;
import by.tms.insta.util.ConnectionJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Denis Smirnov on 28.04.2023
 */
public class JDBCUserDAO implements UserDAO {

    private final ConnectionJdbc connectionJdbc = ConnectionJdbc.getInstance();
    private static final String INSERT_USER = "insert into users(username, password, name, photo, email) " +
            "values (?, ?, ?, ?, ?)";
    private static final String DELETE_USER = "delete from users where user_id = ?";
    private static final String EXTRACT_ALL_USERS = "select * from users";
    private static final String EXTRACT_CURRENT_USER = "select * from users where username = ?";
    private static final String EXTRACT_USER_BY_ID = "select * from users where user_id = ?";
    private static final String EXTRACT_USER_FOLLOWERS = "select u.name, u.username, u.email, u.photo from " +
            "followers f join users u on f.child_id = u.user_id where f.parent_id = ?";
    private static final String EXTRACT_USER_FOLLOWED = "select u.name, u.username, u.email, u.photo from " +
            "followers f join users u on f.parent_id = u.user_id where f.child_id = ?";
    private static final String EXTRACT_COUNT_OF_USERS_FOLLOWERS = "select count (*) from followers where parent_id = ? ";
    private static final String EXTRACT_COUNT_OF_USER_FOLLOWED = "select count (*) from followers where child_id = ?";

    private static final String FOLLOW_ON_USER = "insert into followers (parent_id, child_id) values (?, ?)";
    private static final String UNFOLLOW_FROM_USER = "delete from followers where parent_id = ? and child_id = ?";
    private static final String IS_FOLLOWER = "select count (*) from followers "
            + "join users followed on followers.child_id = followed.user_id "
            + "join users follower on followers.parent_id = follower.user_id "
            + "where followed.username = ? and follower.username = ?";
    private static final String UPDATE_USER = "UPDATE users SET (name, photo, email) VALUES (?,?,?) WHERE user_id = ?";
    private static final String UPDATE_WITH_PASSWORD = "UPDATE users SET (name, photo, email, password) VALUES (?,?,?,?) WHERE user_id = ?";

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
    public void save(User user) throws SQLException {

        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getAvatar());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.execute();
    }

    @Override
    public void deleteById(int userId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(DELETE_USER);
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
    }


    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connectionJdbc.getPostgresConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(EXTRACT_ALL_USERS);
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            userList.add(buildUser(resultSet));
        }
        return userList;
    }


    @Override
    public Optional<User> findByUsername(String username) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_CURRENT_USER);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(buildUser(resultSet));
        }
        return Optional.empty();
    }


    @Override
    public Optional<User> findByUserId(int userId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_USER_BY_ID);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return Optional.of(buildUser(resultSet));
        }
        return Optional.empty();
    }


    @Override
    public List<User> extractFollowers(int userId) throws SQLException {
        List<User> followersList = new ArrayList<>();
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_USER_FOLLOWERS);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            followersList.add(buildFollower(resultSet));
        }
        return followersList;
    }


    @Override
    public List<User> extractFollowed(int userId) throws SQLException {
        List<User> followedList = new ArrayList<>();
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_USER_FOLLOWED);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            followedList.add(buildFollower(resultSet));
        }
        return followedList;
    }


    @Override
    public int extractCountOfFollowers(int userId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_COUNT_OF_USERS_FOLLOWERS);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        if (resultSet.next()) ;
        count = resultSet.getInt(1);
        return count;
    }


    @Override
    public int extractCountOfFollowed(int userId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_COUNT_OF_USER_FOLLOWED);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        if (resultSet.next()) ;
        count = resultSet.getInt(1);
        return count;
    }


    @Override
    public void follow(int parentId, int childId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(FOLLOW_ON_USER);
        preparedStatement.setInt(1, parentId);
        preparedStatement.setInt(2, childId);
        preparedStatement.execute();
    }


    @Override
    public void unfollow(int parentId, int childId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(UNFOLLOW_FROM_USER);
        preparedStatement.setLong(1, parentId);
        preparedStatement.setLong(2, childId);
        preparedStatement.execute();
    }

    @Override
    public int isFollower(String followedUsername, String followerUsername) {
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(IS_FOLLOWER);
            preparedStatement.setString(1, followedUsername);
            preparedStatement.setString(2, followerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int userId, String name, String email, String avatar) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, avatar);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateWithPassword(int userId, String name, String email, String avatar, String password) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(UPDATE_WITH_PASSWORD);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, avatar);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setLong(4, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String username = resultSet.getString(2);
        String password = resultSet.getString(3);
        String name = resultSet.getString(4);
        String photo = resultSet.getString(5);
        String email = resultSet.getString(6);
        return User.builder()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setName(name)
                .setAvatar(photo)
                .setEmail(email)
                .build();
    }

    private User buildFollower(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString(1);
        String username = resultSet.getString(2);
        String email = resultSet.getString(3);
        String photo = resultSet.getString(4);
        return User.builder()
                .setName(name)
                .setUsername(username)
                .setEmail(email)
                .setAvatar(photo)
                .build();
    }
}
