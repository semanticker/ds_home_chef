package kr.mythings.ds.mychef.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FoodRespository {

    private final EntityManager em;
}
