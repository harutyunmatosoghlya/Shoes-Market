package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.mapper.UserMapper;
import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import am.itspace.shoesmarket.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final FileUtil fileUtil;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String register(SaveUserRequest saveUserRequest, MultipartFile multipartFile) {
        saveUserRequest.setPhoto(fileUtil.fileName(multipartFile));
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
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        currentUser,
                        null,
                        currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    @Override
    public String userPage(CurrentUser currentUser, ModelMap model) {
        if (currentUser == null || currentUser.getUser() == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", currentUser.getUser());
        return "userPage";
    }

    @Override
    public String update(CurrentUser currentUser, SaveUserRequest saveUserRequest, MultipartFile multipartFile) {
        if (currentUser == null || currentUser.getUser() == null) {
            return "redirect:/login";
        }
        User user = userMapper.toEntity(saveUserRequest);
        if (!multipartFile.isEmpty()) {
            user.setPhoto(fileUtil.fileName(multipartFile));
        } else {
            user.setPhoto(currentUser.getUser().getPhoto());
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            user.setEmail(currentUser.getUser().getEmail());
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            user.setPhone(currentUser.getUser().getPhone());
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(currentUser.getUser().getName());
        }
        if (user.getSurname() == null || user.getSurname().isEmpty()) {
            user.setSurname(currentUser.getUser().getSurname());
        }
        if (user.getRole() == null) {
            user.setRole(currentUser.getUser().getRole());
        }
        if (user.getGender() == null) {
            user.setGender(currentUser.getUser().getGender());
        }
        user.setPassword(currentUser.getPassword());
        user.setId(currentUser.getUser().getId());
        log.info("user {}", user);
        userRepository.saveAndFlush(user);
        CurrentUser newCurrentUser = new CurrentUser(user);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        newCurrentUser,
                        null,
                        newCurrentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("new current user {}", newCurrentUser);
        return "redirect:/user";
    }
}