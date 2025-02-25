package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.EditUserRequest;
import am.itspace.shoesmarket.dto.LoginUserDto;
import am.itspace.shoesmarket.dto.SaveUserRequest;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.mapper.UserMapper;
import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.UserService;
import am.itspace.shoesmarket.util.AuthUtil;
import am.itspace.shoesmarket.util.FileUtil;
import am.itspace.shoesmarket.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserUtil userUtil;
    private final AuthUtil authUtil;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String register(SaveUserRequest saveUserRequest, MultipartFile multipartFile) {
        if (userRepository.findByEmail(saveUserRequest.getEmail()).isPresent()) {
            return "/login";
        } else {
            saveUserRequest.setPhoto(fileUtil.fileName(multipartFile));
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
        authUtil.authenticate(user.get());
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
    public String update(CurrentUser currentUser, EditUserRequest editUserRequest, MultipartFile multipartFile) {
        if (currentUser == null || currentUser.getUser() == null) {
            return "redirect:/login";
        }
        User user = userMapper.toEntity(editUserRequest);
        userUtil.updateUserFields(user, currentUser, multipartFile);
        userRepository.saveAndFlush(user);
        authUtil.authenticate(user);
        return "redirect:/user";
    }

    @Override
    public String showUpdatePage(CurrentUser currentUser, ModelMap model) {
        if (currentUser == null) {
            return "redirect:/";
        }
        model.addAttribute("user", currentUser.getUser());
        return "editUser";
    }
}