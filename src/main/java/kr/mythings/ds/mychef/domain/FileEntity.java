package kr.mythings.ds.mychef.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DMC_FILE")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class FileEntity extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private String fileName;

    private String fileSaveName;

    private Long fileSize;

    private String fileExtName;

    @ManyToOne
    @JoinColumn(name="recipe_step_id")
    private RecipeStep recipeStep;


    public FileEntity(Long id, String fileName, String fileSaveName, Long fileSize, String fileExtName, RecipeStep recipeStep) {
        this.id = id;
        this.fileName = fileName;
        this.fileSaveName = fileSaveName;
        this.fileSize = fileSize;
        this.fileExtName = fileExtName;
        this.recipeStep = recipeStep;
    }
}
