package am.itspace.shoesmarket.service;

import am.itspace.shoesmarket.entity.Shoes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasketService {

    void addShoesToBasket(List<Shoes> shoes);

    void getAllShoes(Pageable pageable);

    void deleteShoesFromBasket(int id);
}