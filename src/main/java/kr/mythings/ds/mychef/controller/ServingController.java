package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.form.ServingForm;
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
public class ServingController {
    
    @GetMapping("/serving")
    public String list(){
        return "serving/servingList";
    }

    @GetMapping("/serving/new")
    public String add(Model model){
        model.addAttribute("servingForm", new ServingForm());
        return "serving/createServing";
    }

    @PostMapping("/serving/new")
    public String save(@Valid ServingForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "serving/createServing";
        }

        return "redirect:/";
    }

    @GetMapping("/serving/{id}/edit")
    public String edit(@PathVariable("id") Long servingId, Model model) {

        return "serving/editServing";
    }

    @PostMapping("/serving/{id}/edit")
    public String update(@PathVariable("id") Long servingId, @Valid ServingForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "serving/" + servingId + "/edit";
        }


        return "serving/servingList";
    }


    @GetMapping("/serving/{id}")
    public String detail() {

        return "";
    }

    @DeleteMapping("/serving/{id}")
    public String delete() {
        return "";
    }
}
