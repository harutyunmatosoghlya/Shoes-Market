package am.itspace.shoesmarket.controller;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/add")
    public String addShoesToBasket(@RequestParam List<Shoes> shoes) {
        basketService.addShoesToBasket(shoes);
        return "redirect:/basket";
    }

    @PostMapping
    public String getAllShoes(Pageable pageable) {
        basketService.getAllShoes(pageable);
        return "redirect:/basket";
    }

    @GetMapping("/delete")
    public String deleteShoesFromBasket(@RequestParam("id") int id) {
        basketService.deleteShoesFromBasket(id);
        return "redirect:/basket";
    }
}
