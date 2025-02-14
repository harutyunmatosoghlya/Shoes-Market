package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.security.CurrentUser;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    String register(SaveUserRequest userRequest, MultipartFile multipartFile);

    String login(LoginUserDto loginUserDto);

    String userPage(CurrentUser currentUser, ModelMap model);
}