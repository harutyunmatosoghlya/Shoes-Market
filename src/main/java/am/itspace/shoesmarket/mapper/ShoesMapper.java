package am.itspace.shoesmarket.mapper;

import am.itspace.shoesmarket.dto.SaveShoesRequest;
import am.itspace.shoesmarket.dto.ShoesDto;
import am.itspace.shoesmarket.entity.Shoes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoesMapper {

    ShoesDto toShoesDto(Shoes shoes);

    List<ShoesDto> tShoesDto(List<Shoes> shoes);

    Shoes toEntity(SaveShoesRequest ShoesRequest);
}
