package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.domain.Customer;
import kr.mythings.ds.mychef.form.CustomerDTO;
import kr.mythings.ds.mychef.form.CustomerForm;
import kr.mythings.ds.mychef.service.CustomerService;
import kr.mythings.ds.mychef.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    public String list(Model model){
        List<CustomerDTO> list = customerService.list();

        model.addAttribute("list", list);
        return "customer/listCustomer";
    }

    @GetMapping("/customer/new")
    public String add(Model model){
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/createCustomer";
    }

    @PostMapping("/customer/new")
    public String save(@Valid CustomerForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "customer/createCustomer";
        }

        Customer customer = new Customer(form.getName());
        customerService.add(customer);

        return "redirect:/";
    }

    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable("id") Long customerId, Model model) {

        return "customer/editCustomer";
    }

    @PostMapping("/customer/{id}/edit")
    public String update(@PathVariable("id") Long customerId, @Valid CustomerForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "customer/" + customerId + "/edit";
        }


        return "customer/customerList";
    }


    @GetMapping("/customer/{id}")
    public String detail(@PathVariable("id") Long customerId, Model model) {

        Customer customer = customerService.findOne(customerId);
        CustomerForm form = new CustomerForm();
        form.setId(customer.getId());
        form.setName(customer.getName());

        model.addAttribute("form", form);

        return "customer/viewCustomer";
    }

    @DeleteMapping("/customer/{id}")
    public String delete() {
        return "";
    }
}
