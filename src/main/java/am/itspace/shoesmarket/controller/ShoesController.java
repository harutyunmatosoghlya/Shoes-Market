package am.itspace.shoesmarket.controller;

import am.itspace.shoesmarket.dto.ShoesDto;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shoes")
@RequiredArgsConstructor
public class ShoesController {

    private final ShoesService shoesService;

    @GetMapping("/add")
    public String creatShoesPage() {
        return "shoes/add";
    }

    @RequestMapping
    private String getAllShoes(ModelMap modelMap, Pageable pageable) {
        shoesService.allShoes(pageable);
        return "redirect:/shoes";
    }

    @PostMapping("/add")
    public String creatShoes(@ModelAttribute Shoes shoes) {
        shoesService.CreatShoes(shoes);
        return "redirect:/shoes";
    }

    @GetMapping("/update/{id}")
    public String updateShoesPage(@PathVariable int id, @ModelAttribute ShoesDto shoesDto) {
            shoesService.updateShoes(id, shoesDto);
            return "shoes/updateShoes";
    }

    @PostMapping("/update/{id}")
    public String updateShoes(@PathVariable int id, @ModelAttribute ShoesDto shoesDto) {
        shoesService.updateShoes(id, shoesDto);
        return "redirect:/shoes";
    }

    @GetMapping("/delete")
    public String deleteShoes(@RequestParam("id") int id) {
        shoesService.deleteShoesById(id);
        return "redirect:/shoes";
    }
}
