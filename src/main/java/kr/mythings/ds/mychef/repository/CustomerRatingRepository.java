package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.CustomerRating;
import kr.mythings.ds.mychef.domain.RecipeStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CustomerRatingRepository {

    private final EntityManager em;

    public void add(CustomerRating customerRating) {
        em.persist(customerRating);
    }

}
