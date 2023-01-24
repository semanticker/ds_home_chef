package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerForm {

    private Long id;

    @NotEmpty(message = "고객 이름은 필수 입니다.")
    private String name;

    private String active;


}
