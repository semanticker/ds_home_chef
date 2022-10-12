package kr.mythings.ds.mychef.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    private String recipeFrom;

    private String name;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<RecipeStep> recipeStepList = new ArrayList<>();

    public Recipe(String name) {
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.setEnterBy("hyojong-insert");
        this.setEnterDate(now);
        //this.setModifyBy("hyojong-update");
        //this.setModifyDate(LocalDateTime.now().plusHours(2));
    }


}
