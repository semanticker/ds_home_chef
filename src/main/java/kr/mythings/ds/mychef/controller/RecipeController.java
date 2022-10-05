package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.form.FoodForm;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.form.RecipeForm;
import kr.mythings.ds.mychef.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipe")
    public String list(Model model){

        //List<RecipeDTO> list = recipeService.list();

        model.addAttribute("list", Collections.emptyList());

        return "recipe/listRecipe";
    }

    @GetMapping("/recipe/new")
    public String add(Model model){
        model.addAttribute("recipeForm", new RecipeForm());
        return "recipe/createRecipe";
    }

    @PostMapping("/recipe/new")
    public String save(@Valid RecipeForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "recipe/createRecipe";
        }
        
        return "redirect:/";
    }

    @GetMapping("/recipe/{id}/edit")
    public String edit(@PathVariable("id") Long recipeId, Model model) {


        RecipeDTO recipeDTO = recipeService.findOne(recipeId);

        RecipeForm form = new RecipeForm();
        form.setId(recipeDTO.getId());
        form.setName(recipeDTO.getName());
        form.setRecipeFrom(recipeDTO.getRecipeFrom());

        model.addAttribute("form", form);

        return "recipe/editRecipe";
    }

    @PostMapping("/recipe/{id}/edit")
    public String update(@PathVariable("id") Long recipeId, @Valid RecipeForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "recipe/" + recipeId + "/edit";
        }

        recipeService.update(form);

        return "redirect:/recipe";
    }


    @GetMapping("/recipe/{id}")
    public String detail(@PathVariable("id") Long recipeId, Model model) {

        RecipeDTO recipeDTO = recipeService.findOne(recipeId);

        RecipeForm recipeForm = new RecipeForm();
        recipeForm.setId(recipeDTO.getId());
        recipeForm.setName(recipeDTO.getName());
        recipeForm.setRecipeFrom(recipeDTO.getRecipeFrom());

        model.addAttribute("form", recipeForm);

        return "recipe/viewRecipe";
    }

    @DeleteMapping("/recipe/{id}")
    public String delete() {
        return "";
    }
}
