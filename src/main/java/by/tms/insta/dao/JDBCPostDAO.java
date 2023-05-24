package by.tms.insta.dao;

import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.util.ConnectionJdbc;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCPostDAO implements PostDAO {

    private static JDBCPostDAO instance;
    private final ConnectionJdbc connectionJdbc = ConnectionJdbc.getInstance();
    private static final String INSERT_POST = "insert into posts (user_id, image, createdAt, description) values (?,?,?,?)";
    private static final String SELECT_BY_POST_ID = "select * from posts join users on users.user_id = posts.user_id where post_id = ? ";
    private static final String UPDATE_POST = "update posts set (image, description) values (?,?) where post_id = ?";
    private static final String DELETE_BY_POST_ID = "delete from posts where post_id =?";
    private static final String SELECT_BY_USER = "select * from posts join users on users.user_id = posts.user_id where posts.user_id = ? ordered by created_at desc";
    private static final String INSERT_LIKE = "insert into likes (user_id, post_id) values(?,?)";
    private static final String DELETE_LIKE = "delete from likes where user_id = ?";
    private static final String EXTRACT_COUNT_OF_LIKES = "select count (*) from likes where post_id = ?";
    private static final String COUNT_BY_USER = "SELECT count(1) FROM posts WHERE user_id =?";
    private static final String POSTS_BY_USER_WITH_OFFSET = "SELECT * FROM posts JOIN users ON users.user_id = posts.user_id WHERE posts.user_id = ? ORDER BY post_id ASC limit ? offset ?";
    private static final String SELECT_FOLLOWED_USERS_POSTS = "select u.user_id from " +
            "followers f join users u on f.parent_id = u.user_id join posts p on u.user_id = p.user_id where f.child_id = ? order by p.created_at desc limit ? offset ?";
    private static final String SELECT_LIKE_BY_POST_USER_ID = "select * from likes where user_id = ? and post_id = ?";
    private static final String SELECT_GREATEST = "select p.post_id, p.user_id, p.image, p.created_at, p.description, l.post_id AS likesCount from posts p join likes l on p.post_id = l.post_id order by count(likesCount) desc limit 9";

    private JDBCPostDAO() {
    }

    public static JDBCPostDAO getInstance() {
        if (instance == null) {
            instance = new JDBCPostDAO();
        }
        return instance;
    }


    @Override
    public void save(Post post) throws SQLException {
        PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement(INSERT_POST);
        statement.setInt(1, post.getAuthor().getId());
        statement.setString(2, post.getImage());
        statement.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt()));
        statement.setString(4, post.getDescription());
        statement.executeUpdate();
    }

    @Override
    public Optional<Post> findPostById(int id) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(SELECT_BY_POST_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return Optional.of(buildPost(resultSet));
        }
        return Optional.empty();
    }

    public List<Post> findGreatest(){
        try {
            Statement statement = connectionJdbc.getPostgresConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_GREATEST);
            List<Post> postList = new ArrayList<>();
            while (resultSet.next()) {
                postList.add(buildPost(resultSet));
            }
            return postList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePost(int postId, String image, String description) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(UPDATE_POST);
        preparedStatement.setString(1, image);
        preparedStatement.setString(2, description);
        preparedStatement.setLong(3, postId);
        preparedStatement.executeUpdate();

    }


    @Override
    public void deletePost(int id) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(DELETE_BY_POST_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Post> getPostsByUserId(int userId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(SELECT_BY_USER);
        preparedStatement.setLong(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            posts.add(buildPost(resultSet));
        }
        return posts;
    }

    public void like(int userId, int postId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(INSERT_LIKE);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, postId);
        preparedStatement.execute();
    }

    public void unLike(int userId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(DELETE_LIKE);
        preparedStatement.setInt(1, userId);
        preparedStatement.execute();
    }

    public int extractCountOfLikes(int postId) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(EXTRACT_COUNT_OF_LIKES);
        preparedStatement.setInt(1, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count;
    }

    @Override
    public int getCountByUser(int userId) throws SQLException {
        PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement(COUNT_BY_USER);
        statement.setLong(1, userId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Post> getPostsByUserWithOffset(int userId, int limit, int offset) throws SQLException {
        PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement(POSTS_BY_USER_WITH_OFFSET);
        statement.setLong(1, userId);
        statement.setInt(2, limit);
        statement.setInt(3, offset);
        ResultSet resultSet = statement.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            posts.add(buildPost(resultSet));
        }
        return posts;
    }

    public List<Post> getFollowedUsersPosts(int userId, int limit, int offset) throws SQLException {
        PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(SELECT_FOLLOWED_USERS_POSTS);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, limit);
        preparedStatement.setInt(3, offset);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Post> postList = new ArrayList<>();
        while (resultSet.next()) {
            postList.add(buildPost(resultSet));
        }
        return postList;
    }

    public boolean isLiked(int userId, int postId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(SELECT_LIKE_BY_POST_USER_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Post buildPost(ResultSet resultSet) throws SQLException {
        Post post = Post.builder()
                .setId(resultSet.getInt("post_id"))
                .setAuthor(User.builder()
                        .setId(resultSet.getInt("user_id"))
                        .setName(resultSet.getString("name"))
                        .setUsername(resultSet.getString("username"))
                        .setEmail(resultSet.getString("email"))
                        .setAvatar(resultSet.getString("photo"))
                        .build())
                .setImage(resultSet.getString("image"))
                .setDescription(resultSet.getString("description"))
                .setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
        return post;
    }
}