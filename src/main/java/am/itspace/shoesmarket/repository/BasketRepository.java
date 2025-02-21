package am.itspace.shoesmarket.repository;

import am.itspace.shoesmarket.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
}