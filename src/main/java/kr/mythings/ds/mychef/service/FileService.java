package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.FileEntity;
import kr.mythings.ds.mychef.repository.FileRepository;
import kr.mythings.ds.mychef.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    //private String fileDir;

    private final FileRepository fileRepository;

    private final RecipeStepRepository recipeStepRepository;

    public Long saveFile(Long id, MultipartFile multipartFile) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String savedPath = "/Users/asdfiop1517/file_upload/" + uuid + extName;
        String fileSaveName = "";

        long fileSize = multipartFile.getSize();
        String fileExtName = multipartFile.getContentType();

        // 파일 엔티티 생성
        FileEntity file = FileEntity.builder()
                .recipeStep(recipeStepRepository.findOne(id))
                .fileName(fileName)
                .fileSaveName(savedPath)
                .fileSize(fileSize)
                .fileExtName(fileExtName)
                .build();




        // 실제로 로컬에 uuid를 파일명으로 저장
        multipartFile.transferTo(new File(savedPath));

        FileEntity savedFile = fileRepository.save(file);

        return savedFile.getId();
    }

    public FileEntity findOne(Long id) {

        return fileRepository.findOne(id);
    }
}
