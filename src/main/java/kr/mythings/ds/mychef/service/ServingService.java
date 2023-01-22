package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.common.DateFormat;
import kr.mythings.ds.mychef.domain.CustomerRating;
import kr.mythings.ds.mychef.domain.Serving;
import kr.mythings.ds.mychef.form.CustomerRatingDTO;
import kr.mythings.ds.mychef.form.ServingDTO;
import kr.mythings.ds.mychef.form.ServingForm;
import kr.mythings.ds.mychef.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    private final CustomerRepository customerRepository;
    private final CustomerRatingRepository customerRatingRepository;


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
        String servingTime = null;

        if (serving.getServingDate() != null) {
            servingDate = serving.getServingDate().format(DateFormat.DATE_TIME_FORMATTER_YMD);
            servingTime = serving.getServingDate().format(DateFormat.DATE_TIME_FORMATTER_HMS);
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
                ,servingTime
                ,recipeFrom
        );

        servingDTO.setCustomerRatingList(customerRatings);

        return servingDTO;

    }

    public void update(ServingForm form) {

        Long servingId = form.getId();

        Serving serving = servingRepository.findOne(servingId);

        serving.setFood(foodRespository.findOne(Long.valueOf(form.getFoodId())));
        serving.setRecipe(recipeRepository.findOne(Long.valueOf(form.getRecipeId())));

        LocalDateTime servingDate = getLocalDateTimeFromString(form.getServingDate(), form.getServingTime());
        serving.setServingDate(servingDate);

        List<CustomerRatingDTO> customerRatingList = form.getCustomerRatingList();

        for (CustomerRatingDTO customerRatingDTO : customerRatingList) {

            Long ratingId = customerRatingDTO.getId();


            CustomerRating serverRating;

            if (ratingId == null) {
                serverRating = new CustomerRating();
            } else {
                serverRating = customerRatingRepository.find(ratingId);
            }

            if (serverRating == null) {
                serverRating = new CustomerRating();
            }

            serverRating.setServing(servingRepository.findOne(serving.getId()));
            serverRating.setCustomer(customerRepository.findOne(customerRatingDTO.getCustomerId()));

            Long rating = 0L;

            try {
                rating = Long.valueOf(customerRatingDTO.getRating());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            serverRating.setRating(rating);

            customerRatingRepository.update(serverRating);


        }


    }

    public void delete(Long servingId) {
        servingRepository.delete(servingId);
    }

    public void add(ServingForm form) {

        Serving serving = new Serving();

        LocalDateTime servingDate = getLocalDateTimeFromString(form.getServingDate(), form.getServingTime());

        serving.setServingDate(servingDate);
        serving.setFood(foodRespository.findOne(Long.valueOf(form.getFoodId())));
        serving.setRecipe(recipeRepository.findOne(Long.valueOf(form.getRecipeId())));

        servingRepository.save(serving);


        List<CustomerRatingDTO> customerRatingList = form.getCustomerRatingList();

        for (CustomerRatingDTO crt : customerRatingList) {
            CustomerRating cr = new CustomerRating();
            cr.setServing(servingRepository.findOne(serving.getId()));
            cr.setCustomer(customerRepository.findOne(crt.getCustomerId()));
            cr.setRating(Long.valueOf(crt.getRating()));

            customerRatingRepository.add(cr);
        }


    }

    private LocalDateTime getLocalDateTimeFromString(String date, String time) {
        if (date != null && !"".equals(date)) {

            if (time == null || "".equals(time)) {
                time = "00:00:00";
            }

            String strServingDateTime = String.format("%s %s", date, time);
            return LocalDateTime.parse(strServingDateTime, DateFormat.DATE_TIME_FORMATTER_FULL);
        } else {
            return null;
        }
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
