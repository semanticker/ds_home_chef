package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.common.DateFormat;
import kr.mythings.ds.mychef.domain.CustomerRating;
import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.domain.Serving;
import kr.mythings.ds.mychef.form.CustomerRatingDTO;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.form.ServingDTO;
import kr.mythings.ds.mychef.form.ServingForm;
import kr.mythings.ds.mychef.repository.FoodRespository;
import kr.mythings.ds.mychef.repository.RecipeRepository;
import kr.mythings.ds.mychef.repository.ServingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServingService {

    private final FoodRespository foodRespository;
    private final RecipeRepository recipeRepository;
    private final ServingRepository servingRepository;


    public ServingDTO findOne(Long servingId) {
        Serving serving = servingRepository.findOne(servingId);

        Long foodId = null;
        String foodName = null;

        if (serving.getFood() != null) {
            foodId = serving.getFood().getId();
            foodName = serving.getFood().getName();
        }

        Long recipeId = null;
        String recipeName = null ;
        String recipeFrom = null;

        if (serving.getRecipe() != null) {
            recipeId = serving.getRecipe().getId();
            recipeName = serving.getRecipe().getName();
            recipeFrom = serving.getRecipe().getRecipeFrom();
        }

        String servingDate = null;

        if (serving.getServingDate() != null) {
            servingDate = serving.getServingDate().format(DateFormat.DATE_TIME_FORMATTER_YMD);
        }

        List<CustomerRatingDTO> customerRatings = Collections.emptyList();

        List<CustomerRating> customerRatingList = serving.getCustomerRatingList();
        if (serving.getCustomerRatingList() != null && serving.getCustomerRatingList().size() > 0) {
            customerRatings = customerRatingList.stream()
                    .map(m -> new CustomerRatingDTO(
                            m.getId()
                            ,m.getServing().getId()
                            ,m.getCustomer().getId()
                            ,m.getCustomer().getName()
                            ,String.valueOf(m.getRating())
                    ))
                    .collect(Collectors.toList());
        }

        ServingDTO servingDTO = new ServingDTO(
                serving.getId()
                ,String.valueOf(foodId)
                ,foodName
                ,String.valueOf(recipeId)
                ,recipeName
                ,servingDate
                ,recipeFrom
        );

        return servingDTO;

    }

    public void update(ServingForm form) {
    }

    public void delete(Long servingId) {
    }

    public void add(ServingForm form) {

        Serving serving = new Serving();

        String strServingDate = form.getServingDate();

        LocalDateTime servingDate = null;

        if (strServingDate != null && !"".equals(strServingDate)) {

            String servingTime = form.getServingTime();

            if (servingTime == null || "".equals(serving)) {
                servingTime = "00:00:00";
            }

            String strServingDateTime = String.format("%s %s", strServingDate, servingTime);
            servingDate = LocalDateTime.parse(strServingDateTime, DateFormat.DATE_TIME_FORMATTER_FULL);
        }
        serving.setServingDate(servingDate);
        serving.setFood(foodRespository.findOne(Long.valueOf(form.getFoodId())));
        serving.setRecipe(recipeRepository.findOne(Long.valueOf(form.getRecipeId())));

        servingRepository.save(serving);
    }

    public List<ServingDTO> list() {

        List<Serving> list = servingRepository.findAll();

        return list.stream()
                .map(m -> new ServingDTO(
                        m.getId()
                        ,m.getFood().getName()
                        ,m.getRecipe() == null ? "" : m.getRecipe().getName()
                        ,String.valueOf(m.getServingDate())
                        ,m.getRecipe() == null ? "" : m.getRecipe().getRecipeFrom()
                ))
                .collect(Collectors.toList());

    }
}
