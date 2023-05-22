package by.tms.insta.mapper;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.SessionPrincipalUser;
import by.tms.insta.entity.User;


public class UserMapper {
    private UserMapper() {
    }

    public static UserDto toDto (User user){
        UserDto userDto = new UserDto();
        userDto.setId(userDto.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }

    public static User toUser(SessionPrincipalUser sessionPrincipalUser){
        return User.builder()
                .setId(sessionPrincipalUser.getId())
                .setUsername(sessionPrincipalUser.getUsername())
                .setName(sessionPrincipalUser.getName())
                .setEmail(sessionPrincipalUser.getEmail())
                .setAvatar(sessionPrincipalUser.getAvatar())
                .build();
    }

    public static SessionPrincipalUser toSessionPrincipalUserUser(User user){
        return SessionPrincipalUser.builder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setAvatar(user.getAvatar())
                .build();
    }
}