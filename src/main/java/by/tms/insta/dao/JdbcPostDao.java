package by.tms.insta.dao;

import by.tms.insta.entity.Comment;
import by.tms.insta.entity.Post;
import by.tms.insta.entity.User;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostDao implements PostDao {
    private Connection connection;

    public JdbcPostDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createPost(Post post) throws IOException {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO posts (user_id, image, createdAt) VALUES (?,?,?)");
            statement.setString(1, String.valueOf(post.getAuthor().getId())); // почему сетаем стрингу а не инт и просто передаем айди
            statement.setString(2, post.getImage());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt()));
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RemoteException();
        }

    }

    @Override
    public Post findPostById(int id) throws IOException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE id =?");
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
            throw new RemoteException();
        }


    }

    @Override
    public void updatePost(Post post, int postId) throws IOException {


        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE post SET (user_id, image, createdAt) VALUES (?,?,?) WHERE post_id =?  ");
            statement.setLong(1, postId);
            statement.setString(2, post.getImage());
            statement.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt()));
            statement.setLong(4, postId);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new IOException(e);
        }

    }


    @Override
    public void deletePost(int id) throws IOException, SQLException {

        PreparedStatement statement = connection.prepareStatement("DELETE FROM posts WHERE id =?");
        statement.setInt(1, id);
        statement.executeUpdate();

    }

    @Override
    public List<Post> getPostsByUser(User user) throws IOException {
        Post post;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE user_id =?");
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
    public List<Comment> getCommentsByPost(Post post) throws IOException {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE post_id =?");
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
            throw new IOException(e);

        }


    }
}