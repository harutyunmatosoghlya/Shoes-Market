package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.dto.ShoesDto;
import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Shoes;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ShoesService {

    void CreatShoes(Shoes shoes);

    void allShoes(Pageable pageable);

    void updateShoes(int id, ShoesDto shoesDto);

    Optional<Shoes> findShoesById(int id);

    List<Shoes> findShoesByModel(Model model);

    List<Shoes> findShoesByCategory(Category category);

    List<Shoes> findShoesByBrand(String brand);

    List<Shoes> findShoesByPrice(double price);

    List<Shoes> findShoesByRating(double rating);

    void deleteShoesById(int id);
}