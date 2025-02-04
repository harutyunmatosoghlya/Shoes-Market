package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveShoesRequest {

    private int size;
    private String photo;
    private Model model;
    private String description;
    private Category category;
    private String brend;
    private int qty;
    private double price;
    private double rating;


}
