package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.dto.UserDto;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.security.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    public String register(SaveUserRequest userRequest);

    public UserDto login(LoginUserDto loginUserDto, HttpServletRequest request);

    String userPage(CurrentUser currentUser, ModelMap model);
}