package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoesDto {

    private int size;
    private String photo;
    private Model model;
    private String description;
    private Category category;
    private String brand;
    private int qty;
    private double price;
    private double rating;
}