package kr.mythings.ds.mychef.service;

import kr.mythings.ds.mychef.form.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {


    public List<CustomerDTO> list() {
        return null;
    }
}
