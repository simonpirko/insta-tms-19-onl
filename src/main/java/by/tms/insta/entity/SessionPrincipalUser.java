package by.tms.insta.entity;

import by.tms.insta.validation.UserValidation;

/**
 * @author Andrei Lisouski (Andrlis) - 21/05/2023 - 23:30
 */
public class SessionPrincipalUser {

    private int id;
    private String name;
    private String username;
    private String email;
    private String avatar;

    public SessionPrincipalUser() {
    }

    public static SessionPrincipalUserBuilder builder() {
        return new SessionPrincipalUser().new SessionPrincipalUserBuilder();
    }

    public class SessionPrincipalUserBuilder {
        private SessionPrincipalUserBuilder() {
        }

        public SessionPrincipalUser.SessionPrincipalUserBuilder setId(int id) {
            SessionPrincipalUser.this.id = id;
            return this;
        }

        public SessionPrincipalUser.SessionPrincipalUserBuilder setName(String name) {
            SessionPrincipalUser.this.name = name;
            return this;
        }

        public SessionPrincipalUser.SessionPrincipalUserBuilder setUsername(String username) {
            SessionPrincipalUser.this.username = username;
            return this;
        }

        public SessionPrincipalUser.SessionPrincipalUserBuilder setEmail(String email) {
            String validEmail = UserValidation.emailValidation(email);
            SessionPrincipalUser.this.email = validEmail;
            return this;
        }

        public SessionPrincipalUser.SessionPrincipalUserBuilder setAvatar(String avatar) {
            SessionPrincipalUser.this.avatar = avatar;
            return this;
        }

        public SessionPrincipalUser build() {
            return SessionPrincipalUser.this;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
