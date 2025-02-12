package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.dto.UserDto;
import am.itspace.shoesmarket.entity.Role;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.mapper.UserMapper;
import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import am.itspace.shoesmarket.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtTokenUtil;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String register(SaveUserRequest saveUserRequest) {
        if (userRepository.findByEmail(saveUserRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email already in use");
        }
        saveUserRequest.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        log.info("Encoded password for registration: {}", saveUserRequest.getPassword());
        saveUserRequest.setRole(Role.USER);
        userRepository.save(userMapper.toEntity(saveUserRequest));
        return "redirect:/login";
    }

    @Override
    public UserDto login(LoginUserDto loginUserDto, HttpServletRequest request) {
        log.info("Attempting to login with email: {}", loginUserDto.getEmail());
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(() -> {
            log.error("No user found with email: {}", loginUserDto.getEmail());
            return new IllegalArgumentException("email incorrect");
        });
        log.info("User found: {}", user);
        log.info("Attempting to match password: {}", loginUserDto.getPassword());
        boolean passwordMatches = passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword());
        log.info("Password match result: {}", passwordMatches);
        if (!passwordMatches) {
            log.error("Password incorrect for email: {}", loginUserDto.getEmail());
            throw new IllegalArgumentException("password incorrect");
        }
        log.info("Successful login for email: {}", loginUserDto.getEmail());
        String token = jwtTokenUtil.generateToken(user);
        request.setAttribute("Authorization", token);
        return new UserDto(user.getName(),user.getSurname(),user.getEmail(),user.getPhoto(), token);
    }
    @Override
    public String userPage(CurrentUser currentUser, ModelMap model) {
        if (currentUser == null || currentUser.getUser() == null) {
            return "redirect:/login";
        }
        model.put("user", userMapper.toUserDto(currentUser.getUser()));
        return "userPage";
    }
}