package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.EditUserRequest;
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
        authenticate(user.get());
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
        updateUserFields(user, currentUser, multipartFile);
        userRepository.saveAndFlush(user);
        authenticate(user);
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

    private void authenticate(User user) {
        CurrentUser newCurrentUser = new CurrentUser(user);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        newCurrentUser,
                        null,
                        newCurrentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void updateUserFields(User user, CurrentUser currentUser, MultipartFile multipartFile) {
        User current = currentUser.getUser();
        user.setPhoto(getPhoto(multipartFile, current));
        user.setEmail(getOrDefault(user.getEmail(), current.getEmail()));
        user.setPhone(getOrDefault(user.getPhone(), current.getPhone()));
        user.setName(getOrDefault(user.getName(), current.getName()));
        user.setSurname(getOrDefault(user.getSurname(), current.getSurname()));
        user.setRole(getOrDefault(user.getRole(), current.getRole()));
        user.setGender(getOrDefault(user.getGender(), current.getGender()));
        user.setPassword(currentUser.getPassword());
        user.setId(current.getId());
    }

    private String getPhoto(MultipartFile multipartFile, User current) {
        return multipartFile.isEmpty() ? current.getPhoto() : fileUtil.fileName(multipartFile);
    }

    private  <T> T getOrDefault(T value, T defaultValue) {
        return (value == null || (value instanceof String && ((String) value).isEmpty())) ? defaultValue : value;
    }
}