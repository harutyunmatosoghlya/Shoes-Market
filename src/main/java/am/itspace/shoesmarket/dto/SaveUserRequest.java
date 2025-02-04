package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Gender;
import am.itspace.shoesmarket.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class SaveUserRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Gender gender;
    private Role role;

    public SaveUserRequest() {
        this.role = Role.USER;


    }
}
