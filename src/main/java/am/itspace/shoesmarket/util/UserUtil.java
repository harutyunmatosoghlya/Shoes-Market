package am.itspace.shoesmarket.util;

import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final FileUtil fileUtil;

    public void updateUserFields(User user, CurrentUser currentUser, MultipartFile multipartFile) {
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

    public String getPhoto(MultipartFile multipartFile, User current) {
        return multipartFile.isEmpty() ? current.getPhoto() : fileUtil.fileName(multipartFile);
    }

    public <T> T getOrDefault(T value, T defaultValue) {
        return (value == null || (value instanceof String && ((String) value).isEmpty())) ? defaultValue : value;
    }
}
