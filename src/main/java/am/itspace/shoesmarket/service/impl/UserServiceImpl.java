package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}