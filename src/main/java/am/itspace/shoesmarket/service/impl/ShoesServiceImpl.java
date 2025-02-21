package am.itspace.shoesmarket.service.impl;

import am.itspace.shoesmarket.dto.ShoesDto;
import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Shoes;
import am.itspace.shoesmarket.mapper.ShoesMapper;
import am.itspace.shoesmarket.repository.ShoesRepository;
import am.itspace.shoesmarket.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoesServiceImpl implements ShoesService {

    private final ShoesRepository shoesRepository;
    private final ShoesMapper shoesMapper;

    @Override
    public void CreatShoes(Shoes shoes) {
        shoesRepository.save(shoes);
    }

    @Override
    public void getAllShoes(Pageable pageable) {
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
    public void updateShoes(int id, ShoesDto shoesDto) {
        Optional<Shoes> optionalShoes = shoesRepository.findById(id);
        if (optionalShoes.isPresent()) {
            Shoes entity = shoesMapper.toEntity(shoesDto);
            entity.setId(id);
            shoesRepository.save(entity);
        }
        throw new RuntimeException();
    }

    @Override
    public List<Shoes> findShoesByModel(Model model) {
        return shoesRepository.findShoesByModel(model);
    }

    @Override
    public List<Shoes> findShoesByCategory(Category category) {
        return shoesRepository.findShoesByCategory(category);
    }

    @Override
    public List<Shoes> findShoesByBrand(String brand) {
        return shoesRepository.findShoesByBrand(brand);
    }

    @Override
    public List<Shoes> findShoesByPrice(double price) {
        return shoesRepository.findShoesByPrice(price);
    }

    @Override
    public List<Shoes> findShoesByRating(double rating) {
        return shoesRepository.findShoesByRating(rating);
    }
}