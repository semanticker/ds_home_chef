package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.form.RecipeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    @GetMapping("/recipe")
    public String list(){
        return "recipe/recipeList";
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

        


        return "recipe/editRecipe";
    }

    @PostMapping("/recipe/{id}/edit")
    public String update(@PathVariable("id") Long recipeId, @Valid RecipeForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "recipe/" + recipeId + "/edit";
        }


        return "recipe/recipeList";
    }


    @GetMapping("/recipe/{id}")
    public String detail() {

        return "";
    }

    @DeleteMapping("/recipe/{id}")
    public String delete() {
        return "";
    }
}
