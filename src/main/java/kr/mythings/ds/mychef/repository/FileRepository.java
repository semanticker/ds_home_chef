package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public FileEntity save(FileEntity file) {

         em.persist(file);

         return file;

    }
}
