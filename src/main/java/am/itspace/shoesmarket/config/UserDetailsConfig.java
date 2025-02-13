package am.itspace.shoesmarket.config;

import am.itspace.shoesmarket.repository.UserRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsConfig {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByEmail(username)
                .map(CurrentUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
    }
}