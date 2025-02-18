package am.itspace.shoesmarket.repository;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoesRepository extends JpaRepository<Shoes, Integer> {

    Optional<Shoes> findShoesByModel(Model model);

    Optional<Shoes> findShoesByCategory(Category category);

    Optional<Shoes> findShoesByBrand(String brand);

    Optional<Shoes> findShoesByPrice(double price);

    Optional<Shoes> findShoesByRating(double rating);

}
