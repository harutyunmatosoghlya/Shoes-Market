package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.OrderType;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private User user;
    private List<Shoes> shoes;
    private Date acceptedDate;
    private Date approximatelyDate;
    private LocalDateTime arrivedDate;
    private LocalDateTime takenDate;
    private OrderType type;
    private double totalPrice;
    private String address;
}