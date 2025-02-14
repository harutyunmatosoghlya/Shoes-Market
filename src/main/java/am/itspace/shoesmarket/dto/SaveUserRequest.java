package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Gender;
import am.itspace.shoesmarket.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveUserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private Gender gender;
    private String photo;
    private Role role = Role.USER;
}