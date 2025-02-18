package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.dto.OrderDto;
import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.entity.User;

import java.util.List;

public interface OrderService {

    void addOrder (Order order);

    void deleteOrderById(int id);

    List<OrderDto> findByUser(User user);
}
