package com.epam.vrakhmanko.user.mapper;

import com.epam.vrakhmanko.user.dto.UserDto;
import com.epam.vrakhmanko.user.repository.domain.User;
import org.mapstruct.Mapper;
@Mapper(
        componentModel = "spring"
)
public interface PostMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
