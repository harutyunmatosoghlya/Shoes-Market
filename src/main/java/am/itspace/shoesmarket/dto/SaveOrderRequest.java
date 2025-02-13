package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.OrderType;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveOrderRequest {
    private User user;
    private List<Shoes> shoes;
    private Date acceptedDate;
    private Date approximatelyDate;
    private OrderType type;
    private double totalPrice;
    private String address;
}