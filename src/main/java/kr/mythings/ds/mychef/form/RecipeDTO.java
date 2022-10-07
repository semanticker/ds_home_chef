package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDTO {

    private Long id;

    private String name;

    private String foodName;

    private String recipeFrom;

    public RecipeDTO
            (Long id, String name, String foodName, String recipeFrom) {
        this.id = id;
        this.name = name;
        this.foodName= foodName;
        this.recipeFrom= recipeFrom;
    }

    public RecipeDTO() {

    }
}
