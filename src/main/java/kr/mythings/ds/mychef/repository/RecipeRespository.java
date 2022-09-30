package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecipeRespository {

    private final EntityManager em;

    public List<Recipe> findAll(RecipeSearch recipeSearch) {

        String query = "select r from Recipe r";

        return em.createQuery(query, Recipe.class)
                //.setFirstResult(foodSearch.getOffset())
                //.setMaxResults(foodSearch.getLimit())
                .getResultList();
    }
}
