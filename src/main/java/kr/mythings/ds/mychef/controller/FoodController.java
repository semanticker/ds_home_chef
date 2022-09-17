package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.form.FoodForm;
import kr.mythings.ds.mychef.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/food")
    public String list(Model model){

        List<FoodDTO> list = foodService.list();

        model.addAttribute("list", list);

        return "food/listFood";
    }

    @GetMapping("/food/new")
    public String add(Model model){
        model.addAttribute("foodForm", new FoodForm());
        return "food/createFood";
    }

    @PostMapping("/food/new")
    public String save(@Valid FoodForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "food/createFood";
        }

        Food food = new Food(form.getName());

        foodService.add(food);
        return "redirect:/";
    }

    @GetMapping("/food/{id}/edit")
    public String edit(@PathVariable("id") Long foodId, Model model) {

        Food food = foodService.findOne(foodId);

        FoodForm form = new FoodForm();
        form.setId(food.getId());
        form.setName(food.getName());

        model.addAttribute("form", form);

        return "food/editFood";
    }

    @PostMapping("/food/{id}/edit")
    public String update(@PathVariable("id") Long foodId, @Valid FoodForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "food/" + foodId + "/edit";
        }

        foodService.update(form);

        return "food/listFood";
    }


    @GetMapping("/food/{id}")
    public String detail(@PathVariable("id") Long foodId, Model model) {

        Food food = foodService.findOne(foodId);

        FoodForm form = new FoodForm();
        form.setId(food.getId());
        form.setName(food.getName());

        model.addAttribute("form", form);

        return "food/viewFood";
    }

    @DeleteMapping("/food/{id}")
    public String delete() {
        return "";
    }

}
