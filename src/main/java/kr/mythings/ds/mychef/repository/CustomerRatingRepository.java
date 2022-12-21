package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.CustomerRating;
import kr.mythings.ds.mychef.form.CustomerRatingDTO;
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

    public void update(CustomerRating customerRating) {
        em.persist(customerRating);
    }

    public CustomerRating find(Long ratingId) {
        return em.find(CustomerRating.class, ratingId);
    }
}
