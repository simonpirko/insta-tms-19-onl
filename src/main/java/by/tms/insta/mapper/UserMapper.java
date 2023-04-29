package by.tms.insta.mapper;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.User;

import java.util.Optional;

public class UserMapper {
    private final UserDao userDao = JDBCUserDao.getInstance();

    public UserDto toDto (User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
    public User toEntity(UserDto userDto){
        return userDao.findByUsername(userDto.getUsername()).get();
    }
}
