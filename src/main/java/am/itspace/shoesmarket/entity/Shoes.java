package am.itspace.shoesmarket.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "shoes")
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int size;
    private String photo;
    @Enumerated(EnumType.STRING)
    private Model model;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String brend;
    private int qty;
    private double price;
    private double rating;
}
