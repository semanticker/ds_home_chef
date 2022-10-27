package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RecipeStepDTO {

    private Long id;

    private Long recipeId;

    private int step;

    private String howTo;

    private String img;

    private Status status = Status.U;

    private MultipartFile uploadFile;

    public RecipeStepDTO() {}

    public RecipeStepDTO(Long id, Long recipeId, int step, String howTo, String img) {
        this.id = id;
        this.recipeId = recipeId;
        this.step = step;
        this.howTo = howTo;
        this.img = img;
    }
}
