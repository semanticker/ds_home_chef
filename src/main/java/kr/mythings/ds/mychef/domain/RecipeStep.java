package kr.mythings.ds.mychef.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DMC_RECIPE_STEP")
@Getter
@Setter
@NoArgsConstructor
public class RecipeStep {

    @Id
    @GeneratedValue
    @Column(name="recipe_step_id")
    private Long id;

    @Column(name="recipe_id")
    private Long recipeId;

    private int step;

    private String howTo;

    @OneToOne(mappedBy = "recipeStep")
    private FileEntity image;


    public void create(Long recipeId, int step, String howTo) {
        this.recipeId = recipeId;
        this.step = step;
        this.howTo = howTo;
    }
}
