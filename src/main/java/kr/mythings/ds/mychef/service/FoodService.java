package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.form.FoodForm;
import kr.mythings.ds.mychef.repository.FoodRespository;
import kr.mythings.ds.mychef.repository.FoodSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {
    
    private final FoodRespository foodRespository;

    public List<FoodDTO> list() {

        FoodSearch foodSearch = new FoodSearch();
        List<Food> list = foodRespository.findAll(foodSearch);

        List<FoodDTO> collect = list.stream()
                .map(m -> new FoodDTO(m.getId(), m.getName()))
                .collect(Collectors.toList());

        return collect;
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
