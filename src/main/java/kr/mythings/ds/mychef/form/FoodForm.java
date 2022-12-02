package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class FoodForm {

    private Long id;

    @NotEmpty(message = "음식 이름은 필수 입니다.")
    private String name;

}
