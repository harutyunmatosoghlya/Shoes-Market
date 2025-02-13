package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private User user;
    private List<Shoes> shoes;
    private int qty;
    private double totalPrice;
}