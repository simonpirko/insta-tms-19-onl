package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.util.ConnectionJdbc;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCCommentDAO implements CommentDAO {
    private static JDBCCommentDAO instance;
    private final ConnectionJdbc connectionJdbc = ConnectionJdbc.getInstance();
    private static final String INSERT_COMMENT = "insert into comments (message, post_id, user_id, created_at) value(?,?,?,?)";
    private static final String DELETE_COMMENT = "delete from comments where comment_id = ?";
    private static final String UPDATE_COMMENT = "update comments set message = ? where comment_id = ?";
    private static final String SELECT_BY_POST_ID = "select * from comments " +
            "join users on users.user_id = comments.user_id " +
            "where post_id = ? " +
            "ordered by created_at desc " +
            "limit 5 offset ?";
    private static final String SELECT_POST = "select * from posts " +
            "join users on users.user_id = posts.user_id " +
            "where post_id = ? ";

    private JDBCCommentDAO() {
    }

    public static JDBCCommentDAO getInstance() {
        if (instance == null) {
            instance = new JDBCCommentDAO();
        }
        return instance;
    }

    public void save(Comment comment) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(INSERT_COMMENT);
            preparedStatement.setString(1, comment.getMessage());
            preparedStatement.setLong(2, comment.getPost().getId());
            preparedStatement.setLong(3, comment.getAuthor().getId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(comment.getCreatedAt()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int commentId) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(DELETE_COMMENT);
            preparedStatement.setInt(1, commentId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMessageById(int commentId, String message) {
        try {
            PreparedStatement preparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(UPDATE_COMMENT);
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, commentId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Comment> findByPostId(int postId, int paginationOffset) {
        try {
            List<Comment> commentList = new ArrayList<>();

            PreparedStatement postPreparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(SELECT_POST);
            postPreparedStatement.setInt(1, postId);
            ResultSet postResultSet = postPreparedStatement.executeQuery();
            User postUser = User.builder()
                    .setId(postResultSet.getInt("user_id"))
                    .setName(postResultSet.getString("name"))
                    .setUsername(postResultSet.getString("username"))
                    .setEmail(postResultSet.getString("email"))
                    .setAvatar(postResultSet.getString("avatar"))
                    .setPassword(postResultSet.getString("password"))
                    .build();
            Post post = Post.builder()
                    .setId(postId)
                    .setAuthor(postUser)
                    .setImage(postResultSet.getString("image"))
                    .setDescription(postResultSet.getString("description"))
                    .setCreatedAt(postResultSet.getTimestamp("created_at").toLocalDateTime())
                    .build();

            PreparedStatement commentPreparedStatement = connectionJdbc.getPostgresConnection().prepareStatement(SELECT_BY_POST_ID);
            commentPreparedStatement.setInt(1, postId);
            commentPreparedStatement.setInt(2, paginationOffset);
            ResultSet commentResultSet = commentPreparedStatement.executeQuery();

            while (commentResultSet.next()) {
                int comment_id = commentResultSet.getInt("comment_id");
                String message = commentResultSet.getString("message");
                int user_id = commentResultSet.getInt("user_id");
                LocalDateTime time = commentResultSet.getTimestamp("created_at").toLocalDateTime();
                String image = commentResultSet.getString("image");

                User user = User.builder()
                        .setId(commentResultSet.getInt("user_id"))
                        .setName(commentResultSet.getString("name"))
                        .setUsername(commentResultSet.getString("username"))
                        .setEmail(commentResultSet.getString("email"))
                        .setAvatar(commentResultSet.getString("avatar"))
                        .setPassword(commentResultSet.getString("password"))
                        .build();

                Comment comment = Comment.builder()
                        .setId(comment_id)
                        .setMessage(message)
                        .setAuthor(user)
                        .setPost(post)
                        .setCreateAt(time)
                        .build();
                commentList.add(comment);
            }
            return commentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}