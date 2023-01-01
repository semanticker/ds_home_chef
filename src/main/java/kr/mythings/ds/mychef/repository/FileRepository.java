package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.FileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FileRepository {

    private final EntityManager em;

    public FileEntity save(FileEntity file) {

         em.persist(file);

         return file;

    }

    public FileEntity findOne(Long id) {
        return em.find(FileEntity.class, id);
    }

    public void delete(Long fileId) {

        FileEntity one = findOne(fileId);
        em.remove(one);
    }
}
