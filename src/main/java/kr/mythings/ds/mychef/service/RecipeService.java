package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.form.RecipeForm;
import kr.mythings.ds.mychef.form.RecipeStepDTO;
import kr.mythings.ds.mychef.repository.RecipeRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
                        ,m.getRecipeFrom()
                ))
                .collect(Collectors.toList());

    }

    public RecipeDTO findOne(Long recipeId) {

        Recipe recipe = recipeRespository.findOne(recipeId);

        return new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getFood().getName(), recipe.getRecipeFrom());
    }

    public void update(RecipeForm form) {

        Long id = form.getId();
        Recipe recipe = recipeRespository.findOne(id);
        recipe.setName(form.getName());
        recipe.setRecipeFrom(form.getRecipeFrom());
        recipe.setModifyBy("hyojong-update");
        recipe.setModifyDate(LocalDateTime.now());

        List<RecipeStepDTO> recipeStepList = form.getRecipeStepList();

        if (recipeStepList != null && !recipeStepList.isEmpty()) {

            for (RecipeStepDTO recipeStepDTO : recipeStepList) {

                if (recipeStepDTO.getId() == 0L) {
                    // create
                } else {
                    // status에 따른 설정
                }


            }


        }
    }
}
