package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}