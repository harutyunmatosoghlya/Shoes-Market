package am.itspace.shoesmarket.mapper;

import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.dto.UserDto;
import am.itspace.shoesmarket.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> users);

    User toEntity(SaveUserRequest userRequest);



}
