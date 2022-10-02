package kr.mythings.ds.mychef.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DMC_RECIPE")
@Getter
@Setter
@NoArgsConstructor
public class Recipe extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="recipe_id")
    private Long id;

    private Long food_id;

    private String recipeFrom;

    private String name;

    public Recipe(String name) {
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.setEnterBy("hyojong-insert");
        this.setEnterDate(now);
        //this.setModifyBy("hyojong-update");
        //this.setModifyDate(LocalDateTime.now().plusHours(2));
    }


}
