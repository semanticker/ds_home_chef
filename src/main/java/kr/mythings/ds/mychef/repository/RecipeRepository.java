package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecipeRepository {

    private final EntityManager em;

    public List<Recipe> findAll() {
        String query = "select r from Recipe r "
                + "join fetch r.food f ";

        return em.createQuery(query, Recipe.class).getResultList();
    }
    public List<Recipe> findAll2() {

        String query = "select r from Recipe r";

        return em.createQuery(query, Recipe.class)
                //.setFirstResult(foodSearch.getOffset())
                //.setMaxResults(foodSearch.getLimit())
                .getResultList();
    }

    public Recipe findOne(Long recipeId) {

        return em.find(Recipe.class, recipeId);
    }

    public void save(Recipe recipe) {
        em.persist(recipe);
    }

    public void delete(Long recipeId) {
        em.remove(em.find(Recipe.class, recipeId));
    }
}
