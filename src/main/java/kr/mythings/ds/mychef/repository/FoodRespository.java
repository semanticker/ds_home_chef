package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
}
