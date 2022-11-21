package kr.mythings.ds.mychef.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id")
    private Food food;

    private String recipeFrom;

    private String name;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    @OrderBy("step ASC")
    private List<RecipeStep> recipeStepList = new ArrayList<>();

    public Recipe(String name) {
        this.name = name;
        LocalDateTime now = LocalDateTime.now();
        this.setEnterBy("hyojong-insert");
        this.setEnterDate(now);
    }


}
