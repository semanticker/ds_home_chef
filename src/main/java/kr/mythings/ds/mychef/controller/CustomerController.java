package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.form.CustomerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    @GetMapping("/customer")
    public String list(){
        return "customer/customerList";
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
    public String detail() {

        return "";
    }

    @DeleteMapping("/customer/{id}")
    public String delete() {
        return "";
    }
}
