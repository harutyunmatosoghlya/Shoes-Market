package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/shoes")
@RequiredArgsConstructor
public class ShoesController {

    private final ShoesService shoesService;

    @GetMapping("/add")
    public String addShoesPage() {
        return "shoes/add";
    }

    @GetMapping("/shoes")
    private String showShoesPage(ModelMap modelMap, Pageable pageable) {
        shoesService.allShoes(pageable);
        return "shoes/shoes";
    }

    @PostMapping("/add")
    public String addShoes(@ModelAttribute Shoes shoes) {
        shoesService.addShoes(shoes);
        return "redirect:/shoes";
    }

    @GetMapping("/update/{id}")
    public String updateShoesPage(@PathVariable int id, ModelMap modelMap) {
        Optional<Shoes> shoes = shoesService.findShoesById(id);
        if (shoes.isPresent()) {
            modelMap.put("shoes", shoes);
            return "shoes/updateShoes";
        }
        return "redirect:/shoes";
    }

    @PostMapping("/update")
    public String updateShoes(@ModelAttribute Shoes shoes) {
        shoesService.updateShoes(shoes);
        return "redirect:/shoes";
    }

    @GetMapping("/delete")
    public String deleteShoes(@RequestParam("id") int id) {
        shoesService.deleteShoesById(id);
        return "redirect:/shoes";
    }
}
