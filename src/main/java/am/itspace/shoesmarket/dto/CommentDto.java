package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentDto {

    private User user;
    private Shoes shoes;
    private String comment;
    private Date time;

}
