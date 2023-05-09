package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;
import by.tms.insta.util.ConnectionJdbc;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostDAO implements PostDAO {

    private static JDBCPostDAO instance;
    private final ConnectionJdbc connectionJdbc = ConnectionJdbc.getInstance();

    private JDBCPostDAO() {
    }

    public static JDBCPostDAO getInstance() {
        if (instance == null) {
            instance = new JDBCPostDAO();
        }
        return instance;
    }


    @Override
    public void createPost(Post post) {

        try {
            PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement("INSERT INTO posts (user_id, image, createdAt) VALUES (?,?,?)");
            statement.setString(1, String.valueOf(post.getAuthor().getId()));
            statement.setString(2, post.getImage());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt()));
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public Post findPostById(int id) {
        try {
            PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement("SELECT * FROM posts WHERE id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Post post = null;
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                post = Post.builder().setId(resultSet.getInt("id")).setAuthor(User.builder().build()) // todo before add UserDAO
                        .setImage(resultSet.getString("image"))
                        .setCreatedAt(Timestamp.valueOf(resultSet.getString("createdAt")).toLocalDateTime()).build();


            }
            return post;
        } catch (SQLException e) {
            throw new RuntimeException();
        }


    }

    @Override
    public void updatePost(Post post, int postId) {
        try {

            PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement("UPDATE post SET (user_id, image, createdAt) VALUES (?,?,?) WHERE post_id =?  ");
            statement.setLong(1, postId);
            statement.setString(2, post.getImage());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt()));
            statement.setLong(4, postId);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void deletePost(int id) {
        try {
            PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement("DELETE FROM posts WHERE id =?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        Post post;
        try {
            PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement("SELECT * FROM posts WHERE user_id =?");
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                post = Post.builder().setId(resultSet.getInt("id")).setAuthor(user)
                        .setImage(resultSet.getString("image"))
                        .setCreatedAt(Timestamp.valueOf(resultSet.getString("createdAt")).toLocalDateTime()).build();

                posts.add(post);

            }
            return posts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Comment> getCommentsByPost(Post post) {

        try {
            PreparedStatement statement = connectionJdbc.getPostgresConnection().prepareStatement("SELECT * FROM comments WHERE post_id =?");
            statement.setInt(1, (int) post.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Comment> comments = new ArrayList();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("post_id");
                Comment comment = Comment.builder()
                        .setId(resultSet.getInt("id"))
                        .setAuthor(User.builder().build())// todo before add UserDAO)
                        .setPost(post)
                        .setMessage(resultSet.getString("message"))
                        .setCreateAt(Timestamp.valueOf(resultSet.getString("createdAt")).toLocalDateTime()).build();


                comments.add(comment);

            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}