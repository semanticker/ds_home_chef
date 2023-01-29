package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.common.SuccessResult;
import kr.mythings.ds.mychef.common.ThumbnailGenerator;
import kr.mythings.ds.mychef.common.ThumbnailSize;
import kr.mythings.ds.mychef.config.StorageConfig;
import kr.mythings.ds.mychef.domain.FileEntity;
import kr.mythings.ds.mychef.repository.FileRepository;
import kr.mythings.ds.mychef.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    private final RecipeStepRepository recipeStepRepository;


    private final StorageConfig storageConfig;

    public Long saveFile(Long id, MultipartFile multipartFile) throws IOException, NullPointerException {


        String path = storageConfig.getPath();

        String fileName = multipartFile.getOriginalFilename();

        if (fileName != null) {


            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
            String uuid = UUID.randomUUID().toString();
            String savedPath = path + File.pathSeparator + uuid + "." + fileExtName;

            long fileSize = multipartFile.getSize();
            String contentType = multipartFile.getContentType();

            // 파일 엔티티 생성
            FileEntity file = FileEntity.builder()
                    .recipeStep(recipeStepRepository.findOne(id))
                    .fileName(fileName)
                    .fileSaveName(savedPath)
                    .fileSize(fileSize)
                    .contentType(contentType)
                    .fileExtName(fileExtName)
                    .build();


            File savedFile = new File(savedPath);
            // 실제로 로컬에 uuid를 파일명으로 저장
            multipartFile.transferTo(savedFile);

            ThumbnailGenerator.generate(savedFile, ThumbnailSize.SMALL);

            FileEntity savedFileEntity = fileRepository.save(file);

            return savedFileEntity.getId();
        } else {
            throw new NullPointerException("File Name is NULL!!");
        }


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

            if (!"".equals(fileSaveName)) {


                if (deleteFile(fileSaveName)) {
                    fileRepository.delete(fileId);

                    deleteFile(ThumbnailGenerator.getThumbnailName(fileSaveName, ThumbnailSize.SMALL));
                    deleteFile(ThumbnailGenerator.getThumbnailName(fileSaveName, ThumbnailSize.MEDIUM));
                    deleteFile(ThumbnailGenerator.getThumbnailName(fileSaveName, ThumbnailSize.LARGE));

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

    private boolean deleteFile(String path) {
        try {
            Files.delete(Paths.get(path));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
