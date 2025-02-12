package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.dto.UserDto;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth")
    public String authenticate(@ModelAttribute LoginUserDto loginUserDto, ModelMap model, HttpServletRequest request) {
        UserDto userDto = userService.login(loginUserDto, request);
        model.addAttribute("currentUser", userDto);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute SaveUserRequest saveUserRequest) {
        return userService.register(saveUserRequest);
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap model) {
        return userService.userPage(currentUser, model);
    }
}