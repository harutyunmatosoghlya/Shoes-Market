package am.itspace.shoesmarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    private List<Shoes> shoes;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acceptedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approximatelyDate;
    private LocalDateTime arrivedDate;
    private LocalDateTime takenDate;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    private double totalPrice;
    private String address;
}