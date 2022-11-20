package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FoodRespository {

    private final EntityManager em;

    public void add(Food food) {
        em.persist(food);
    }

    public Food findOne(Long foodId) {
        return em.find(Food.class, foodId);
    }

    public List<Food> findAll() {

        String query = "select f from Food f";

        return em.createQuery(query, Food.class)
                //.setFirstResult(foodSearch.getOffset())
                //.setMaxResults(foodSearch.getLimit())
                .getResultList();
    }

    public void delete(Long foodId) {
        em.remove(em.find(Food.class, foodId));
    }
}
