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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String fileName;

    private String fileSaveName;

    private Long fileSize;

    private String fileExtName;

    public FileEntity(Long id, String fileName, String fileSaveName, Long fileSize, String fileExtName) {
        this.id = id;
        this.fileName = fileName;
        this.fileSaveName = fileSaveName;
        this.fileSize = fileSize;
        this.fileExtName = fileExtName;
    }
}
