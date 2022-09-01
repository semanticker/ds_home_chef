package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.form.FoodForm;
import kr.mythings.ds.mychef.repository.FoodRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    public Food findOne(Long foodId) {
        return foodRespository.findOne(foodId);
    }

    public void update(FoodForm form) {

        Long id = form.getId();
        Food one = findOne(id);
        one.setName(form.getName());
        one.setModifyBy("hyojong-update");
        one.setModifyDate(LocalDateTime.now());

    }
}
