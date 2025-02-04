package am.itspace.shoesmarket.mapper;

import am.itspace.shoesmarket.dto.SaveBasketRequest;
import am.itspace.shoesmarket.entity.Basket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasketMapper {

  SaveBasketRequest toBasketDto (Basket basket);

    List<SaveBasketRequest> toBasketDto(List<Basket> basket);

    Basket toEntity(SaveBasketRequest basketRequest);
}
