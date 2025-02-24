package am.itspace.shoesmarket.repository;

import am.itspace.shoesmarket.dto.OrderDto;
import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<OrderDto> findByUser(User user);
}
