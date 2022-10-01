package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDTO {

    private Long id;

    private String name;

    public RecipeDTO
            (Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
