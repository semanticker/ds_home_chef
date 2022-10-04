package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RecipeForm {

    private Long id;

    @NotEmpty(message = "레시피 이름은 필수 입니다.")
    private String name;

    private String recipeFrom;
}
