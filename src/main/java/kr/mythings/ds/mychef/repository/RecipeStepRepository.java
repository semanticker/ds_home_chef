package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.RecipeStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RecipeStepRepository {

    private final EntityManager em;

    public void add(RecipeStep recipeStep) {
        em.persist(recipeStep);
    }

    public RecipeStep findOne(Long id) {
        return em.find(RecipeStep.class, id);
    }

    public void remove(Long id) {
        em.remove(findOne(id));
    }
}
