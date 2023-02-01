package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.FileEntity;
import kr.mythings.ds.mychef.domain.Recipe;
import kr.mythings.ds.mychef.domain.RecipeStep;
import kr.mythings.ds.mychef.form.*;
import kr.mythings.ds.mychef.repository.FoodRespository;
import kr.mythings.ds.mychef.repository.RecipeRepository;
import kr.mythings.ds.mychef.repository.RecipeStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {

    private final FoodRespository foodRespository;
    private final RecipeRepository recipeRepository;
    private final RecipeStepRepository recipeStepRepository;

    private final FileService fileService;
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
        recipeDTO.setFoodId(String.valueOf(recipe.getFood().getId()));
        recipeDTO.setFoodName(recipe.getFood().getName());

        List <RecipeStepDTO> recipeStepDTOList = new ArrayList<>();

        List<RecipeStep> recipeStepList = recipe.getRecipeStepList();

        if (recipeStepList != null && !recipeStepList.isEmpty()) {

            for (RecipeStep recipeStep : recipeStepList) {

                String imagePath = null;

                FileEntity image = recipeStep.getImage();

                if (image != null) {
                    imagePath = String.valueOf(image.getId());
                }

                RecipeStepDTO recipeStepDTO = new RecipeStepDTO(
                        recipeStep.getId(),
                        recipeStep.getRecipeId(),
                        recipeStep.getStep(),
                        recipeStep.getHowTo(),
                        imagePath
                );
                recipeStepDTOList.add(recipeStepDTO);
            }
        }


        recipeDTO.setRecipeStepList(recipeStepDTOList);

        return recipeDTO;
    }

    public void update(RecipeForm form) throws IOException, NullPointerException {

        Long recipeId = form.getId();
        Recipe recipe = recipeRepository.findOne(recipeId);
        recipe.setName(form.getName());
        recipe.setFood(foodRespository.findOne(Long.valueOf(form.getFoodId())));
        recipe.setRecipeFrom(form.getRecipeFrom());
        recipe.setModifyBy("hyojong-update");
        recipe.setModifyDate(LocalDateTime.now());

        List<RecipeStepDTO> recipeStepList = form.getRecipeStepList();

        if (recipeStepList != null && !recipeStepList.isEmpty()) {

            int stepNo = 1;
            for (RecipeStepDTO recipeStepDTO : recipeStepList) {

                switch (recipeStepDTO.getStatus()) {
                    case I :
                        addRecipeStep(recipeId, stepNo++, recipeStepDTO);
                        break;
                    case U:
                        updateRecipeStep(stepNo++, recipeStepDTO);
                        break;
                    case D:
                        deleteRecipeStep(recipeStepDTO.getId());
                        break;
                }

                saveAttachFile(recipeStepDTO);
            }
        }
    }

    /**
     * 첨부파일을 추가한다.
     * @param recipeStepDTO
     * @throws IOException
     */
    private void saveAttachFile(RecipeStepDTO recipeStepDTO) throws IOException {
        MultipartFile img = recipeStepDTO.getImg();
        if (img != null && img.getOriginalFilename() != null && !"".equals(img.getOriginalFilename())) {
            fileService.saveFile(recipeStepDTO.getId(),img);
        }
    }

    /**
     * 레시피 단계를 삭제한다.
     * @param id
     */
    private void deleteRecipeStep(Long id) {

        if (id != null) {
            recipeStepRepository.remove(id);
        }
    }

    /**
     * 레시피 단계를 수정한다.
     * @param stepNo
     * @param recipeStepDTO
     */
    private void updateRecipeStep(int stepNo, RecipeStepDTO recipeStepDTO) {
        RecipeStep recipeStep = recipeStepRepository.findOne(recipeStepDTO.getId());
        recipeStep.setStep(stepNo);
        recipeStep.setHowTo(recipeStepDTO.getHowTo());
    }

    /**
     * 레시피 단계를 추가한다.
     * @param recipeId
     * @param stepNo
     * @param recipeStepDTO
     */
    private void addRecipeStep(long recipeId, int stepNo, RecipeStepDTO recipeStepDTO) {
        RecipeStep recipeStep = new RecipeStep();
        recipeStep.create(
                recipeId,
                stepNo,
                recipeStepDTO.getHowTo()
        );
        recipeStepRepository.add(recipeStep);

        recipeStepDTO.setId(recipeStep.getId());
    }


    public void add(RecipeForm recipeForm) {

        Recipe recipe = new Recipe();
        recipe.setFood(foodRespository.findOne(Long.valueOf(recipeForm.getFoodId())));
        recipe.setName(recipeForm.getName());
        recipe.setRecipeFrom(recipeForm.getRecipeFrom());
        recipe.setEnterBy("hyojong-insert");
        recipe.setEnterDate(LocalDateTime.now());

        recipeRepository.save(recipe);
    }

    public void delete(Long recipeId) {

        Recipe one = recipeRepository.findOne(recipeId);

        List<RecipeStep> recipeStepList = one.getRecipeStepList();

        if (recipeStepList != null && !recipeStepList.isEmpty()) {
            for (RecipeStep rs : recipeStepList) {
                FileEntity image = rs.getImage();
                fileService.delete(image.getId());
            }

        }


        recipeRepository.delete(recipeId);
    }

    public List<RecipeDTO> findFoodRecipeList(Long foodId) {
        return recipeRepository.findFoodRecipe(foodId);
    }

    public List<ListTypeDTO> getRecipeCodeList(Long foodId) {

        List<RecipeDTO> list = this.findFoodRecipeList(foodId);

        return list.stream().map(m->new ListTypeDTO(String.valueOf(m.getId()), m.getName())).collect(Collectors.toList());
    }
}
