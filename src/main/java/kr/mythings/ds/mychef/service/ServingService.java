package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Serving;
import kr.mythings.ds.mychef.form.ServingDTO;
import kr.mythings.ds.mychef.form.ServingForm;
import kr.mythings.ds.mychef.repository.FoodRespository;
import kr.mythings.ds.mychef.repository.ServingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServingService {

    private final FoodRespository foodRespository;
    private final ServingRepository servingRepository;




    public ServingDTO findOne(Long servingId) {
        return null;
    }

    public void update(ServingForm form) {
    }

    public void delete(Long servingId) {
    }

    public void add(ServingForm form) {

        Serving serving = new Serving();
        serving.setServingDate(form.getServingDate());
        serving.setFood(foodRespository.findOne(Long.valueOf(form.getFoodId())));


        servingRepository.save(serving);
    }
}
