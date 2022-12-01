package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.form.RecipeForm;
import kr.mythings.ds.mychef.form.ServingDTO;
import kr.mythings.ds.mychef.form.ServingForm;
import kr.mythings.ds.mychef.service.ServingService;
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

@Controller
@RequiredArgsConstructor
public class ServingController {

    private final ServingService servingService;
    
    @GetMapping("/serving")
    public String list(Model model){

        model.addAttribute("list", Collections.emptyList());

        return "serving/listServing";
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

        return "redirect:/serving";
    }

    @GetMapping("/serving/{id}/edit")
    public String edit(@PathVariable("id") Long servingId, Model model) {

        ServingDTO servingDTO = servingService.findOne(servingId);

        ServingForm servingForm = new ServingForm();
        servingForm.setId(servingDTO.getId());
        model.addAttribute("form", servingForm);

        return "serving/editServing";
    }

    @PostMapping("/serving/{id}/edit")
    public String update(@PathVariable("id") Long servingId, @Valid ServingForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return String.format("serving/%s/edit", servingId);
        }

        servingService.update(form);

        return "redirect:/serving";
    }


    @GetMapping("/serving/{id}")
    public String detail(@PathVariable("id") Long servingId, Model model) {

        ServingDTO servingDTO = servingService.findOne(servingId);

        ServingForm servingForm = new ServingForm();
        servingForm.setId(servingId);

        model.addAttribute("form", servingForm);
        return "serving/viewServing";
    }

    @GetMapping("/serving/{id}/delete")
    public String delete(@PathVariable("id") Long servingId) {

        servingService.delete(servingId);

        return "redirect:/serving";
    }
}
