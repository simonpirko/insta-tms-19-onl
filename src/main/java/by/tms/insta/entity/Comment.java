package by.tms.insta.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Denis Smirnov
 */
public class Comment {
    private int id;
    private String message;
    private User author;
    private Post post;
    private LocalDateTime createdAt;

    private Comment() {
    }

    public static Comment.CommentBuilder builder() {
        return new Comment().new CommentBuilder();
    }

    public class CommentBuilder {
        private CommentBuilder() {
        }

        public Comment.CommentBuilder setId(int id) {
            Comment.this.id = id;
            return this;
        }

        public Comment.CommentBuilder setMessage(String message) {
            Comment.this.message = message;
            return this;
        }

        public Comment.CommentBuilder setAuthor(User author) {
            Comment.this.author = author;
            return this;
        }

        public Comment.CommentBuilder setPost(Post post) {
            Comment.this.post = post;
            return this;
        }

        public Comment.CommentBuilder setCreateAt(LocalDateTime createAt) {
            Comment.this.createdAt = createAt;
            return this;
        }
        public Comment build() {
            return Comment.this;
        }
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", author=" + author +
                ", post=" + post +
                ", createdAt=" + createdAt +
                '}';
    }
}
