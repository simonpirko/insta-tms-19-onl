package by.tms.insta.mapper;

import by.tms.insta.dto.UserDto;
import by.tms.insta.entity.User;


public class UserMapper {
    private UserMapper() {
    }

    public static UserDto toDto (User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
}