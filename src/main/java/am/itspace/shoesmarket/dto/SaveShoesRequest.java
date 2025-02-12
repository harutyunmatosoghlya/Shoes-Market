package am.itspace.shoesmarket.dto;

import am.itspace.shoesmarket.entity.Category;
import am.itspace.shoesmarket.entity.Model;
import am.itspace.shoesmarket.entity.Size;
import am.itspace.shoesmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveShoesRequest {
    private List<Size> sizes;
    private String photo;
    private Model model;
    private String description;
    private Category category;
    private String brand;
    private long qty;
    private double price;
    private double rating;
    private User user;
}