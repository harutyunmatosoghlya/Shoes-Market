package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.repository.ShoesRepository;
import am.itspace.shoesmarket.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoesServiceImpl implements ShoesService {

    private final ShoesRepository shoesRepository;

    @Override
    public void addShoes(Shoes shoes) {
        shoesRepository.save(shoes);
    }

    @Override
    public void allShoes(Pageable pageable) {
        shoesRepository.findAll(pageable);
    }

    @Override
    public Optional<Shoes> findShoesById(int id) {
        return shoesRepository.findById(id);
    }

    @Override
    public void deleteShoesById(int id) {
        shoesRepository.deleteById(id);
    }

    @Override
    public void updateShoes(Shoes shoes) {
        shoesRepository.saveAndFlush(shoes);
    }

    @Override
    public Optional<Shoes> findShoesByModel(Model model) {
        return shoesRepository.findShoesByModel(model);
    }

    @Override
    public Optional<Shoes> findShoesByCategory(Category category) {
        return shoesRepository.findShoesByCategory(category);
    }

    @Override
    public Optional<Shoes> findShoesByBrand(String brand) {
        return shoesRepository.findShoesByBrand(brand);
    }

    @Override
    public Optional<Shoes> findShoesByPrice(double price) {
        return shoesRepository.findShoesByPrice(price);
    }

    @Override
    public Optional<Shoes> findShoesByRating(double rating) {
        return shoesRepository.findShoesByRating(rating);
    }
}