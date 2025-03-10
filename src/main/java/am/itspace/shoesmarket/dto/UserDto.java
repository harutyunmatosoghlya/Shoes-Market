package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String photo;
    private Role role;
}