package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.OrderDto;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.entity.User;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/add")
    public String addOrderPage() {
        return "order/add";
    }

    @PostMapping("/add")
    public String addShoes(@ModelAttribute Order order) {
        orderService.addOrder(order);
        return "redirect:/shoes";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("id") int id) {
        orderService.deleteOrderById(id);
        return "redirect:/shoes";
    }

    @GetMapping("/user/order")
    public String findByUser(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        List<OrderDto> orders = orderService.findByUser(currentUser.getUser());
        modelMap.put("orders", orders);
        return "redirect:/user/order";
    }
}