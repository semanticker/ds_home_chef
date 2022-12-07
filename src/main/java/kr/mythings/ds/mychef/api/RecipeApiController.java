package kr.mythings.ds.mychef.api;

import kr.mythings.ds.mychef.form.ListTypeDTO;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RecipeApiController extends ApiController{

    private final RecipeService recipeService;

    @GetMapping("/api/v2/recipes")
    public Result foodsV2() {

        List<RecipeDTO> list = recipeService.list();

        return new Result(list.size(), list);
    }

    @GetMapping(value = "/api/v2/code/recipes/{id}", produces = "application/json; charset=UTF-8")
    public Result edit(@PathVariable("id") Long foodId) {
        List<RecipeDTO> list = recipeService.findFoodRecipeList(foodId);

        List<ListTypeDTO> codeList = list.stream().map(m->new ListTypeDTO(String.valueOf(m.getId()), m.getName())).collect(Collectors.toList());
        return new Result(codeList.size(), codeList);
    }

}
