package am.itspace.shoesmarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private User user_id;
    @ManyToOne
    private Shoes shoes_id;
    private String comment;
    @Enumerated(EnumType.STRING)
    private CommentType type;
    private Date time;
}
