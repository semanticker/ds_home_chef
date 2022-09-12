package kr.mythings.ds.mychef;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.service.FoodService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FoodServiceTest {

    @Autowired
    FoodService foodService;

    @Test
    public void 음식저장() {

        Food food = new Food();
        food.setName("된장국");

        Long saveId = foodService.add(food);
        Food one = foodService.findOne(saveId);

        assertEquals(food, one);
    }

}
