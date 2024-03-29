package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.form.ListTypeDTO;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.form.RecipeForm;
import kr.mythings.ds.mychef.service.FoodService;
import kr.mythings.ds.mychef.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private static final String REDIRECT_RECIPE = "redirect:/recipe";
    private final FoodService foodService;
    private final RecipeService recipeService;

    @GetMapping("/recipe")
    public String list(Model model){

        model.addAttribute("list", Collections.emptyList());

        return "recipe/listRecipe";
    }

    @GetMapping("/recipe/new")
    public String add(Model model){

        List<ListTypeDTO> foodCodeList = foodService.getFoodCodeList();

        model.addAttribute("foodCodeList", foodCodeList);
        model.addAttribute("recipeForm", new RecipeForm());
        return "recipe/createRecipe";
    }

    @PostMapping("/recipe/new")
    public String save(@Valid RecipeForm recipeForm, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/recipe/new";
        }

        recipeService.add(recipeForm);
        
        return REDIRECT_RECIPE;
    }

    @GetMapping("/recipe/{id}/edit")
    public String edit(@PathVariable("id") Long recipeId, Model model) {

        RecipeDTO recipeDTO = recipeService.findOne(recipeId);

        RecipeForm form = new RecipeForm();
        form.setId(recipeDTO.getId());
        form.setFoodId(recipeDTO.getFoodId());
        form.setFoodName(recipeDTO.getFoodName());
        form.setName(recipeDTO.getName());
        form.setRecipeFrom(recipeDTO.getRecipeFrom());
        form.setRecipeStepList(recipeDTO.getRecipeStepList());

        List<ListTypeDTO> foodCodeList = foodService.getFoodCodeList();

        model.addAttribute("foodCodeList", foodCodeList);
        model.addAttribute("form", form);

        return "recipe/editRecipe";
    }

    @PostMapping("/recipe/{id}/edit")
    public String update(@PathVariable("id") Long recipeId, @Valid @ModelAttribute("recipeForm") RecipeForm form, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return String.format("recipe/%s/edit", recipeId);
        }

        recipeService.update(form);

        return REDIRECT_RECIPE;
    }


    @GetMapping("/recipe/{id}")
    public String detail(@PathVariable("id") Long recipeId, Model model) {

        RecipeDTO recipeDTO = recipeService.findOne(recipeId);

        RecipeForm recipeForm = new RecipeForm();
        recipeForm.setId(recipeDTO.getId());
        recipeForm.setName(recipeDTO.getName());
        recipeForm.setFoodName(recipeDTO.getFoodName());
        recipeForm.setRecipeFrom(recipeDTO.getRecipeFrom());
        recipeForm.setRecipeStepList(recipeDTO.getRecipeStepList());

        model.addAttribute("form", recipeForm);

        return "recipe/viewRecipe";
    }

    @GetMapping("/recipe/{id}/delete")
    public String delete(@PathVariable("id") Long recipeId) {

        recipeService.delete(recipeId);

        return REDIRECT_RECIPE;
    }
}
