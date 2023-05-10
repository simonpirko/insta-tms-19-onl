package by.tms.insta.entity;

import by.tms.insta.validation.UserValidation;

import java.util.Objects;

/**
 * @author Denis Smirnov
 */
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String avatar;


    private User() {
    }

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {
        private UserBuilder() {
        }

        public UserBuilder setId(int id) {
            User.this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setUsername(String username) {
            User.this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {

            String validPassword = UserValidation.passwordValidation(password);
            User.this.password = validPassword;
            return this;
        }

        public UserBuilder setEmail(String email) {
            String validEmail = UserValidation.emailValidation(email);
            User.this.email = validEmail;
            return this;
        }

        public UserBuilder setAvatar(String avatar) {
            User.this.avatar = avatar;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
