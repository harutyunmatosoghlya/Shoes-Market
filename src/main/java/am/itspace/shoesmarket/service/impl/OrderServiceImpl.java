package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.OrderDto;
import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.repository.OrderRepository;
import am.itspace.shoesmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> findByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
