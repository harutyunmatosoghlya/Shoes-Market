package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
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
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Value("${user.image.uploadPath}")
    private String uploadPath;
    private Path path = Paths.get("userImage");

    @GetMapping("/login")
    public String showLoginPage() {
        log.info("path {}", path);
        return "login";
    }

    @PostMapping("/auth")
    public String authenticate(@ModelAttribute LoginUserDto user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute SaveUserRequest user, @RequestParam("image") MultipartFile multipartFile) {
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
}