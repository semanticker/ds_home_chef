package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {

    private Long id;

    private String name;

    public FoodDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
