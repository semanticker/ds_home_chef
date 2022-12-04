package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.form.*;
import kr.mythings.ds.mychef.service.CustomerService;
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
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ServingController {

    private final ServingService servingService;
    private final CustomerService customerService;

    @GetMapping("/serving")
    public String list(Model model){

        model.addAttribute("list", Collections.emptyList());

        return "serving/listServing";
    }

    @GetMapping("/serving/new")
    public String add(Model model){


        /*
        List<RecipeStepDTO> collect = recipe.getRecipeStepList().stream()
                .map(m -> new RecipeStepDTO(
                        m.getId(),
                        m.getRecipeId(),
                        m.getStep(),
                        m.getHowTo(),
                        m.getImg()
//Long id, Long recipeId, int step, String howTo, String img
                ))
                .collect(Collectors.toList());

         */

        ServingForm servingForm = new ServingForm();

        List<CustomerDTO> list = customerService.list();
        List<CustomerRatingDTO> collect = list.stream()
                .map(m -> new CustomerRatingDTO(m.getId(), m.getName())).collect(Collectors.toList());

        servingForm.setCustomerRatingList(collect);



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
