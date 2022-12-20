package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.common.DateFormat;
import kr.mythings.ds.mychef.form.*;
import kr.mythings.ds.mychef.service.CustomerService;
import kr.mythings.ds.mychef.service.FoodService;
import kr.mythings.ds.mychef.service.RecipeService;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ServingController {

    private final FoodService foodService;

    private final RecipeService recipeService;


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

        List<ListTypeDTO> foodCodeList = foodService.getFoodCodeList();

        List<CustomerDTO> list = customerService.list();
        List<CustomerRatingDTO> collect = list.stream()
                .map(m -> new CustomerRatingDTO(m.getId(), m.getName())).collect(Collectors.toList());
        servingForm.setCustomerRatingList(collect);

        LocalDateTime ldt = LocalDateTime.now();

        servingForm.setServingDate(ldt.format(DateFormat.DATE_TIME_FORMATTER_YMD));
        servingForm.setServingTime(ldt.format(DateFormat.DATE_TIME_FORMATTER_HMS));

        model.addAttribute("foodCodeList", foodCodeList);
        model.addAttribute("customerList", collect);
        model.addAttribute("servingForm", servingForm);
        return "serving/createServing";
    }

    @PostMapping("/serving/new")
    public String save(@Valid ServingForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "serving/createServing";
        }

        servingService.add(form);

        return "redirect:/serving";
    }

    @GetMapping("/serving/{id}/edit")
    public String edit(@PathVariable("id") Long servingId, Model model) {

        ServingDTO servingDTO = servingService.findOne(servingId);

        ServingForm servingForm = new ServingForm();

        List<ListTypeDTO> foodCodeList = foodService.getFoodCodeList();
        List<ListTypeDTO> recipeCodeList = Collections.emptyList();

        if (servingDTO.getFoodId() != null && !"".equals(servingDTO.getFoodId())) {
            recipeCodeList = recipeService.getRecipeCodeList(Long.valueOf(servingDTO.getFoodId()));
        }

        List<CustomerRatingDTO> customerRatingList = servingDTO.getCustomerRatingList();

        if (customerRatingList == null || customerRatingList.size() == 0) {
            List<CustomerDTO> list = customerService.list();
            customerRatingList = list.stream()
                    .map(m -> new CustomerRatingDTO(
                            m.getId()
                            , m.getName())
                    ).collect(Collectors.toList());
        }

        servingForm.setCustomerRatingList(customerRatingList);

        servingForm.setFoodId(servingDTO.getFoodId());
        servingForm.setRecipeId(servingDTO.getRecipeId());
        servingForm.setServingDate(servingDTO.getServingDate());
        servingForm.setServingTime(servingDTO.getServingTime());

        model.addAttribute("foodCodeList", foodCodeList);
        model.addAttribute("recipeCodeList", recipeCodeList);
        model.addAttribute("customerList", customerRatingList);
        ///model.addAttribute("servingForm", servingForm);

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
        servingForm.setId(servingDTO.getId());
        servingForm.setFoodId(servingDTO.getFoodId());
        servingForm.setFoodName(servingDTO.getFoodName());
        servingForm.setRecipeId(servingDTO.getRecipeId());
        servingForm.setRecipeName(servingDTO.getRecipeName());
        servingForm.setRecipeFrom(servingDTO.getRecipeFrom());
        servingForm.setServingDate(servingDTO.getServingDate());

        List<CustomerRatingDTO> customerRatingList = servingDTO.getCustomerRatingList();
        servingForm.setCustomerRatingList(customerRatingList);


        model.addAttribute("form", servingForm);
        model.addAttribute("customerList", customerRatingList);

        return "serving/viewServing";
    }

    @GetMapping("/serving/{id}/delete")
    public String delete(@PathVariable("id") Long servingId) {

        servingService.delete(servingId);

        return "redirect:/serving";
    }
}
