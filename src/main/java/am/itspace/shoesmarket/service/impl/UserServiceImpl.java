package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.mapper.UserMapper;
import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String register(SaveUserRequest saveUserRequest) {
        if (userRepository.findByEmail(saveUserRequest.getEmail()).isPresent()) {
            return "/login";
        } else {
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