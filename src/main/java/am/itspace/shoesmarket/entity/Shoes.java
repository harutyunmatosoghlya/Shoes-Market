package am.itspace.shoesmarket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @OneToMany
    private List<Size> sizes;
    private String photo;
    @Enumerated(EnumType.STRING)
    private Model model;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String brand;
    private int qty;
    private double price;
    private double rating;
    @ManyToOne
    private User user;
}
