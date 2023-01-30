package kr.mythings.ds.mychef.api;

import kr.mythings.ds.mychef.form.ServingDTO;
import kr.mythings.ds.mychef.service.ServingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ServingApiController extends ApiController{

    private final ServingService servingService;

    @GetMapping(value = "/api/v2/servings", produces = "application/json; charset=UTF-8")
    public Result<ServingDTO> servingsV2() {

        List<ServingDTO> list = servingService.list();

        return new Result(list.size(), list);
    }
}
