package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeForm {

    private Long id;

    @NotEmpty(message = "음식은 필수 입력 입니다.")
    private String foodId;

    private String foodName;

    @NotEmpty(message = "레시피 이름은 필수 입니다.")
    private String name;

    private String recipeFrom;

    private List<RecipeStepDTO> recipeStepList = new ArrayList<>();
}
