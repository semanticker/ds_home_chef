package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeStepDTO {

    private Long id;

    private Long recipeId;

    private int step;

    private String howTo;

    private String img;

    private Status status;
}
