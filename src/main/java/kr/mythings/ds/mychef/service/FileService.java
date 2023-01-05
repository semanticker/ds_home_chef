package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.common.SuccessResult;
import kr.mythings.ds.mychef.domain.FileEntity;
import kr.mythings.ds.mychef.repository.FileRepository;
import kr.mythings.ds.mychef.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public SuccessResult delete(Long fileId) {

        boolean success = false;
        String msg = "";

        FileEntity fileEntity = findOne(fileId);

        if (fileEntity != null) {

            String fileSaveName = fileEntity.getFileSaveName();

            if ("".equals(fileSaveName)) {

                File file = new File(fileSaveName);

                if (file.exists() && file.isFile()) {

                    success = file.delete();
                    fileRepository.delete(fileId);

                } else {
                    msg = "대상이 되는 파일이 존재하지 않습니다.";
                }

            } else {
                msg = "대상이 되는 파일 위치를 찾을수 없습니다.";
            }
        } else {
            msg = "대상이 되는 파일 정보를 찾을수 없습니다.";
        }

        return new SuccessResult(success, msg);
    }
}
