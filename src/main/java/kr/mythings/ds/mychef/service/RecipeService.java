package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.repository.FoodRespository;
import kr.mythings.ds.mychef.repository.FoodSearch;
import kr.mythings.ds.mychef.repository.RecipeRespository;
import kr.mythings.ds.mychef.repository.RecipeSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRespository recipeRespository;
    public List<RecipeDTO> list() {


        RecipeSearch recipeSearch = new RecipeSearch();
        List<Recipe> list = recipeRespository.findAll(recipeSearch);

        List<RecipeDTO> collect = list.stream()
                .map(m -> new RecipeDTO(m.getId(), m.getName()))
                .collect(Collectors.toList());

        return collect;

    }
}
