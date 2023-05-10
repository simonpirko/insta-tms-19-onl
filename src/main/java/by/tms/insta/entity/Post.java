package by.tms.insta.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Denis Smirnov
 */
public class Post {
    private int id;
    private User author;
    private String image;
    private String description;
    private LocalDateTime createdAt;
    private List<Comment> commentList;

    private Post() {
    }

    public static Post.PostBuilder builder() {
        return new Post().new PostBuilder();
    }

    public class PostBuilder {
        private PostBuilder() {
        }

        public Post.PostBuilder setId(int id) {
            Post.this.id = id;
            return this;
        }

        public Post.PostBuilder setAuthor(User author) {
            Post.this.author = author;
            return this;
        }

        public Post.PostBuilder setImage(String image) {
            Post.this.image = image;
            return this;
        }

        public Post.PostBuilder setDescription(String description) {
            Post.this.description = description;
            return this;
        }

        public Post.PostBuilder setCreatedAt(LocalDateTime createdAt) {
            Post.this.createdAt = createdAt;
            return this;
        }

        public Post.PostBuilder commentList(List<Comment> commentList) {
            Post.this.commentList = commentList;
            return this;
        }

        public Post build() {
            return Post.this;
        }

    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() { return description; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", commentList=" + commentList +
                '}';
    }
}
