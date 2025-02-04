package am.itspace.shoesmarket.mapper;

import am.itspace.shoesmarket.dto.OrderDto;
import am.itspace.shoesmarket.dto.SaveOrderRequest;
import am.itspace.shoesmarket.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(Order order);

    List<OrderDto> toOrderDto(List<Order> orders);

    Order toEntity(SaveOrderRequest orderRequest);
}
