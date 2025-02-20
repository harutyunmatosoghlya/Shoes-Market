package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.entity.Order;
import am.itspace.shoesmarket.security.CurrentUser;
import am.itspace.shoesmarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/add")
    public String creatOrderPage() {
        return "order/add";
    }

    @PostMapping("/add")
    public String creatShoes(@ModelAttribute Order order) {
        orderService.CreatOrder(order);
        return "redirect:/shoes";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("id") int id) {
        orderService.deleteOrderById(id);
        return "redirect:/shoes";
    }

    @GetMapping("/user/order")
    public String findByUser(@AuthenticationPrincipal CurrentUser currentUser, ModelMap model) {
        orderService.findByUser(currentUser, model);
        return "redirect:/user/order";
    }
}