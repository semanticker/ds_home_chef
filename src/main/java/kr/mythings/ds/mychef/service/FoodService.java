package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.repository.FoodRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {
    
    private final FoodRespository foodRespository;

    public void list() {
    }

    public Long add(Food food) {
        foodRespository.add(food);

        return food.getId();
    }
}
