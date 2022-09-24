package kr.mythings.ds.mychef.api;

import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodApiController extends ApiController{

    private final FoodService foodService;

    @GetMapping("/api/v1/foods")
    public Result foodsV1() {

        List<FoodDTO> list = foodService.list();

        return new Result(list.size(), list);
    }

    @GetMapping("/api/v2/foods")
    public Result foodsV2() {

        List<FoodDTO> list = foodService.list();

        return new Result(list.size(), list);
    }


}
