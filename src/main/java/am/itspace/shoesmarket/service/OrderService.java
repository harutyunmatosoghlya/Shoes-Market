package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.security.CurrentUser;
import org.springframework.ui.ModelMap;

public interface OrderService {

    void CreatOrder(Order order);

    void deleteOrderById(int id);

    void findByUser(CurrentUser currentUser, ModelMap modelMap);
}
