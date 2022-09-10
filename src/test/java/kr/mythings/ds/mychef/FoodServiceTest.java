package kr.mythings.ds.mychef;

import kr.mythings.ds.mychef.service.FoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FoodServiceTest {

    @Autowired
    FoodService foodService;

    @Test
    public void aa() {
        foodService.list();
    }

}
