package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.CommentType;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SaveCommentRequest {

    private User user;
    private Shoes shoes;
    private String comment;
    private Date time;
    private CommentType type;
}