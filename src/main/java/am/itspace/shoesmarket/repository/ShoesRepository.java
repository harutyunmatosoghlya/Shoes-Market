package am.itspace.shoesmarket.repository;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoesRepository extends JpaRepository<Shoes, Integer> {

    List<Shoes> findShoesByModel(Model model);

    List<Shoes> findShoesByCategory(Category category);

    List<Shoes> findShoesByBrand(String brand);

    List<Shoes> findShoesByPrice(double price);

    List<Shoes> findShoesByRating(double rating);

}
