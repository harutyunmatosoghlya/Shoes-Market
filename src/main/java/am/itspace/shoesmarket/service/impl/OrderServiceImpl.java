package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.OrderDto;
import am.itspace.shoesmarket.dto.UserDto;
import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.repository.OrderRepository;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void CreatOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void findByUser(CurrentUser currentUser, ModelMap modelMap) {
        User user = currentUser.getUser();
        List<OrderDto> orders = orderRepository.findByUser(user);
        modelMap.put("orders", orders);
    }
}
