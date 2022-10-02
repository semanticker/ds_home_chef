package kr.mythings.ds.mychef.api;

import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.service.FoodService;
import kr.mythings.ds.mychef.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeApiController extends ApiController{

    private final RecipeService recipeService;

    @GetMapping("/api/v2/recipes")
    public Result foodsV2() {

        List<RecipeDTO> list = recipeService.list();

        return new Result(list.size(), list);
    }

}
