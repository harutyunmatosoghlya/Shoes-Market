package am.itspace.shoesmarket.util;

import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.security.CurrentUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    public void authenticate(User user) {
        CurrentUser newCurrentUser = new CurrentUser(user);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                newCurrentUser,
                null,
                newCurrentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}