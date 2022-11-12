package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.domain.Customer;
import kr.mythings.ds.mychef.form.CustomerDTO;
import kr.mythings.ds.mychef.form.FoodDTO;
import kr.mythings.ds.mychef.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDTO> list() {

        List<Customer> list = customerRepository.findAll();


        List<CustomerDTO> collect = list.stream()
                .map(m -> new CustomerDTO(m.getId(), m.getName()))
                .collect(Collectors.toList());

        return collect;
    }
}
