package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ServingForm {

    private Long id;

    private String foodId;

    private String foodName;

    private String recipeId;

    private String recipeName;

    private String recipeFrom;

    private List<CustomerRatingDTO> customerRatingList = new ArrayList();

    private LocalDateTime servingDate;

}
