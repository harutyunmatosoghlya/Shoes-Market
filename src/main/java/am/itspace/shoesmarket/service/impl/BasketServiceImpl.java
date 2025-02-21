package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.SaveBasketRequest;
import am.itspace.shoesmarket.entity.Basket;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.mapper.BasketMapper;
import am.itspace.shoesmarket.repository.BasketRepository;
import am.itspace.shoesmarket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;

    @Override
    public void addShoesToBasket(List<Shoes> shoes) {
        Basket basket = new Basket();
        basket.setShoes(shoes);
        basketRepository.save(basket);
    }

    @Override
    public void getAllShoes(Pageable pageable) {
        basketRepository.findAll(pageable);
    }

    @Override
    public void deleteShoesFromBasket(int id) {
        basketRepository.deleteById(id);
    }
}