package kr.mythings.ds.mychef.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Getter
@Setter
public class RecipeStepDTO {

    private Long id;

    private Long recipeId;

    private int step;

    private String howTo;

    private MultipartFile img;

    private Status status = Status.U;

    //private MultipartFile uploadFile;
    private String imagePath;

    public RecipeStepDTO() {}

    public RecipeStepDTO(Long id, Long recipeId, int step, String howTo, String imagePath) {
        this.id = id;
        this.recipeId = recipeId;
        this.step = step;
        this.howTo = howTo;
        this.imagePath = imagePath;
    }
}
