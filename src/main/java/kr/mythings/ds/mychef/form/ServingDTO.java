package kr.mythings.ds.mychef.form;

import lombok.Data;

@Data
public class ServingDTO {

    private Long id;

    private String foodId;

    private String foodName;

    private String recipeId;

    private String recipeName;

    private String servingDate;

    public ServingDTO(Long id, String foodName, String recipeName, String servingDate) {
        this.id = id;
        this.foodName = foodName;
        this.recipeName = recipeName;
        this.servingDate = servingDate;
    }
}
