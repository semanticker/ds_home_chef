package kr.mythings.ds.mychef.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServingDTO {

    private Long id;

    private String foodId;

    private String foodName;

    private String recipeId;

    private String recipeName;

    private String servingDate;

    private String recipeFrom;

    private List<CustomerRatingDTO> customerRatingList = new ArrayList();

    public ServingDTO(Long id, String foodName, String recipeName, String servingDate, String recipeFrom) {
        this.id = id;
        this.foodId = foodId;
        this.foodName = foodName;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.servingDate = servingDate;
        this.recipeFrom = recipeFrom;
    }

    public ServingDTO(Long id, String foodName, String recipeName, String servingDate, String recipeFrom, List<CustomerRatingDTO> customerRatingList ) {
        this.id = id;
        this.foodName = foodName;
        this.recipeName = recipeName;
        this.servingDate = servingDate;
        this.recipeFrom = recipeFrom;
        this.customerRatingList = customerRatingList;
    }

    public ServingDTO(Long id, String foodId, String foodName, String recipeId, String recipeName, String servingDate, String recipeFrom) {
        this.id = id;
        this.foodId = foodId;
        this.foodName = foodName;
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.servingDate = servingDate;
        this.recipeFrom = recipeFrom;
    }
}
