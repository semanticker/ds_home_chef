package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.repository.RecipeRespository;
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


        List<Recipe> list = recipeRespository.findAll();

        return list.stream()
                .map(m -> new RecipeDTO(
                        m.getId()
                        ,m.getName()
                        ,m.getFood().getName()
                ))
                .collect(Collectors.toList());

    }

    public RecipeDTO findOne(Long recipeId) {

        Recipe recipe = recipeRespository.findOne(recipeId);

        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getFood().getName());
    }
}
