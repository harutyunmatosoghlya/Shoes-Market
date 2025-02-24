package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Value("${user.image.uploadPath}")
    private String uploadPath;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/auth")
    public String authenticate(@ModelAttribute LoginUserDto user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute SaveUserRequest user, @RequestParam("image") MultipartFile multipartFile) {
        return userService.register(user, multipartFile);
    }

    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap model) {
        return userService.userPage(currentUser, model);
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imageName) throws IOException {
        String filePath = uploadPath + File.separator + imageName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "file not found: " + imageName);
        }
        return Files.readAllBytes(file.toPath());
    }

    @GetMapping("/user/edit")
    public String editUserPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap model) {
        if (currentUser == null) {
            return "redirect:/";
        }
        model.addAttribute("user", currentUser.getUser());
        return "editUser";
    }

    @PostMapping("/user/edit")
    public String editUser(@AuthenticationPrincipal CurrentUser currentUser, @Valid @ModelAttribute SaveUserRequest user, @RequestParam("image") MultipartFile multipartFile) {
        return userService.update(currentUser, user, multipartFile);
    }
}