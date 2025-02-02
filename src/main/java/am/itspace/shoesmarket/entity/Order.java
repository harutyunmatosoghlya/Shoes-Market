package am.itspace.shoesmarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Shoes shoes;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acceptedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approxmatelyDate;
    private Date arrivedDate;
    private Date takenDate;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    private double totalPrice;
    private String address;
}