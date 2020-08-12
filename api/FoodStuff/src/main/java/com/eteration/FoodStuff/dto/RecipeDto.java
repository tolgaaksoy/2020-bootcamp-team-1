package com.eteration.FoodStuff.dto;

import com.eteration.FoodStuff.model.Ingredient;
import lombok.*;
import javax.persistence.Lob;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto extends BaseDto {
    private String title;
    @Lob
    private String description;
    private String prepTime;
    private String serving;
    @Lob
    private String chefTips;
    @Lob
    private String image;
    private UserDto user;
    private List<Ingredient> ingredients;
    private List<DirectionDto> directions;
}