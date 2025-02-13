package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth")
    public String authenticate(@ModelAttribute LoginUserDto user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute SaveUserRequest user) {
        return userService.register(user);
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap model) {
        return userService.userPage(currentUser, model);
    }
}