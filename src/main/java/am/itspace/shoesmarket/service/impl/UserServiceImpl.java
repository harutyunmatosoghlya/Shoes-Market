package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.mapper.UserMapper;
import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Value("${user.image.uploadPath}")
    private String uploadPath;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String register(SaveUserRequest saveUserRequest, MultipartFile multipartFile) {
        String fileName;
        if (!multipartFile.isEmpty()) {
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            File destinationFile = new File(uploadDir, fileName);
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при сохранении файла", e);
            }

            saveUserRequest.setPhoto(fileName);
        }
        if (userRepository.findByEmail(saveUserRequest.getEmail()).isPresent()) {
            return "/login";
        } else {
            log.info("photo name {}", saveUserRequest.getPhoto());
            saveUserRequest.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
            userRepository.save(userMapper.toEntity(saveUserRequest));
            return "redirect:/";
        }
    }


    @Override
    public String login(LoginUserDto loginUserDto) {
        Optional<User> user = userRepository.findByEmail(loginUserDto.getEmail());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.get().getPassword())) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        CurrentUser currentUser = new CurrentUser(user.get());
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    @Override
    public String userPage(CurrentUser currentUser, ModelMap model) {
        if (currentUser == null || currentUser.getUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser.getUser());
        return "/userPage";
    }
}