package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.OrderType;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderDto {

    private User user;
    private Shoes shoes;
    private Date acceptedDate;
    private Date approxmatelyDate;
    private String arrivedDate;
    private String takenDate;
    private OrderType type;
    private double totalPrice;
    private String address;
}