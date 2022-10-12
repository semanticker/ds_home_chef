package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeDTO {

    private Long id;

    private String name;

    private String foodName;

    private String recipeFrom;

    private List<RecipeStepDTO> recipeStepList = new ArrayList<>();

    public RecipeDTO
            (Long id, String name, String foodName, String recipeFrom, List recipeStepList) {
        this.id = id;
        this.name = name;
        this.foodName= foodName;
        this.recipeFrom= recipeFrom;
        this.recipeStepList = recipeStepList;
    }

    // recipe.getId(), recipe.getName(), recipe.getFood().getName(), recipe.getRecipeFrom()

    public RecipeDTO(Long id, String name, String foodName, String recipeFrom) {
        this.id = id;
        this.name = name;
        this.foodName= foodName;
        this.recipeFrom= recipeFrom;
    }
}
