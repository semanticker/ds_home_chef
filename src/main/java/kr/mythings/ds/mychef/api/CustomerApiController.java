package kr.mythings.ds.mychef.api;

import kr.mythings.ds.mychef.form.CustomerDTO;
import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerApiController {

    private final CustomerService customerService;

    @GetMapping("/api/v2/customers")
    public ApiController.Result foodsV2() {

        List<CustomerDTO> list = customerService.list();

        return new ApiController.Result(list.size(), list);
    }
}
