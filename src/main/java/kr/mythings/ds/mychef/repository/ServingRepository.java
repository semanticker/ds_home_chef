package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.domain.Serving;
import kr.mythings.ds.mychef.form.ServingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ServingRepository {

    private final EntityManager em;

    public void save(Serving serving) {
        em.persist(serving);
    }

    public List<Serving> findAll() {

        String query = "select r from Serving r "
                + "join fetch r.food f ";

        return em.createQuery(query, Serving.class).getResultList();


    }

    public Serving findOne(Long servingId) {
        return em.find(Serving.class, servingId);
    }

    public void delete(Long servingId) {
        em.remove(em.find(Serving.class, servingId));
    }
}
