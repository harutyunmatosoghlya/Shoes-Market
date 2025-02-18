package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Shoes;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ShoesService {

    void addShoes(Shoes shoes);

    void allShoes(Pageable pageable);

    void updateShoes(Shoes shoes);

    Optional<Shoes> findShoesById(int id);

    Optional<Shoes> findShoesByModel(Model model);

    Optional<Shoes> findShoesByCategory(Category category);

    Optional<Shoes> findShoesByBrand(String brand);

    Optional<Shoes> findShoesByPrice(double price);

    Optional<Shoes> findShoesByRating(double rating);

    void deleteShoesById(int id);
}