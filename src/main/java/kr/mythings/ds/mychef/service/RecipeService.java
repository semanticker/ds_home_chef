package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.domain.RecipeStep;
import kr.mythings.ds.mychef.form.RecipeDTO;
import kr.mythings.ds.mychef.form.RecipeForm;
import kr.mythings.ds.mychef.form.RecipeStepDTO;
import kr.mythings.ds.mychef.form.Status;
import kr.mythings.ds.mychef.repository.RecipeRepository;
import kr.mythings.ds.mychef.repository.RecipeStepRepository;
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

    private final RecipeRepository recipeRepository;
    private final RecipeStepRepository recipeStepRepository;
    public List<RecipeDTO> list() {


        List<Recipe> list = recipeRepository.findAll();

        return list.stream()
                .map(m -> new RecipeDTO(
                        m.getId()
                        ,m.getName()
                        ,m.getFood().getName()
                        ,m.getRecipeFrom()
                        ,null
                ))
                .collect(Collectors.toList());

    }

    public RecipeDTO findOne(Long recipeId) {

        Recipe recipe = recipeRepository.findOne(recipeId);

        RecipeDTO recipeDTO = new RecipeDTO(recipe.getId(), recipe.getName(), recipe.getFood().getName(), recipe.getRecipeFrom());

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

        recipeDTO.setRecipeStepList(collect);

        return recipeDTO;
    }

    public void update(RecipeForm form) {

        Long id = form.getId();
        Recipe recipe = recipeRepository.findOne(id);
        recipe.setName(form.getName());
        recipe.setRecipeFrom(form.getRecipeFrom());
        recipe.setModifyBy("hyojong-update");
        recipe.setModifyDate(LocalDateTime.now());

        List<RecipeStepDTO> recipeStepList = form.getRecipeStepList();

        if (recipeStepList != null && !recipeStepList.isEmpty()) {

            for (RecipeStepDTO recipeStepDTO : recipeStepList) {

                // 입력
                if (recipeStepDTO.getId() == null && Status.I == recipeStepDTO.getStatus()) {
                    // create
                    RecipeStep recipeStep = new RecipeStep();
                    recipeStep.create(
                            recipeStepDTO.getRecipeId(),
                            recipeStepDTO.getStep(),
                            recipeStepDTO.getHowTo(),
                            recipeStepDTO.getImg()
                    );
                    recipeStepRepository.add(recipeStep);

                } else if(recipeStepDTO.getId() != null && Status.U == recipeStepDTO.getStatus()) {
                    // status 에 따른 설정

                    RecipeStep recipeStep = recipeStepRepository.findOne(recipeStepDTO.getId());
                    recipeStep.setRecipeId(recipeStep.getRecipeId());
                    recipeStep.setStep(recipeStep.getStep());
                    recipeStep.setHowTo(recipeStep.getHowTo());
                    recipeStep.setImg(recipeStep.getImg());



                }


            }


        }
    }
}
