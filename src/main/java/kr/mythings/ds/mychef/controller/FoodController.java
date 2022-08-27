package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/list")
    public String list(){

        foodService.list();

        return "food/foodList";
    }

    @GetMapping("/add")
    public String add(){
        return "";
    }

    @PutMapping("/add")
    public String save() {
        return "";
    }

    @GetMapping("/{id}")
    public String detail() {
        return "";
    }

    @DeleteMapping("/{id}")
    public String delete() {
        return "";
    }

}
