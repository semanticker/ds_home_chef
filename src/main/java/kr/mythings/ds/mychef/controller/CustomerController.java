package kr.mythings.ds.mychef.controller;

import kr.mythings.ds.mychef.domain.Customer;
import kr.mythings.ds.mychef.form.CustomerDTO;
import kr.mythings.ds.mychef.form.CustomerForm;
import kr.mythings.ds.mychef.form.ListTypeDTO;
import kr.mythings.ds.mychef.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private static final String REDIRECT_CUSTOMER_LIST = "redirect:/customer";

    private static final String LIST_ACTIVE = "activeList";


    private final CustomerService customerService;

    @GetMapping("/customer")
    public String list(Model model){
        List<CustomerDTO> list = customerService.list();

        model.addAttribute("list", list);
        return "customer/listCustomer";
    }

    @GetMapping("/customer/new")
    public String add(Model model){

        model.addAttribute(LIST_ACTIVE, getActiveList());
        model.addAttribute("customerForm", new CustomerForm());
        return "customer/createCustomer";
    }

    @PostMapping("/customer/new")
    public String save(@Valid CustomerForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "customer/createCustomer";
        }

        Customer customer = new Customer(form.getName());
        customer.setBirthDate(form.getBirthDate());
        customer.setActive(Boolean.parseBoolean(form.getActive()));
        customerService.add(customer);

        return REDIRECT_CUSTOMER_LIST;
    }

    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable("id") Long customerId, Model model) {
        Customer customer = customerService.findOne(customerId);

        CustomerForm form = new CustomerForm();
        form.setId(customer.getId());
        form.setName(customer.getName());
        form.setActive(String.valueOf(customer.isActive()));
        form.setBirthDate(customer.getBirthDate());

        model.addAttribute(LIST_ACTIVE, getActiveList());
        model.addAttribute("form", form);

        return "customer/editCustomer";

    }

    private List<ListTypeDTO> getActiveList() {

        List<ListTypeDTO> activeList = new ArrayList<>();
        activeList.add(new ListTypeDTO("true","활성화"));
        activeList.add(new ListTypeDTO("false","비활성화"));

        return activeList;
    }

    @PostMapping("/customer/{id}/edit")
    public String update(@PathVariable("id") Long customerId, @Valid CustomerForm form, BindingResult result) {

        String path = "/customer/" + customerId + "/edit";

        if (result.hasErrors()) {
            return path;
        }

        customerService.update(form);

        return REDIRECT_CUSTOMER_LIST;
    }


    @GetMapping("/customer/{id}")
    public String detail(@PathVariable("id") Long customerId, Model model) {

        Customer customer = customerService.findOne(customerId);
        CustomerForm form = new CustomerForm();
        form.setId(customer.getId());
        form.setName(customer.getName());
        form.setActive(String.valueOf(customer.isActive()));
        form.setBirthDate(customer.getBirthDate());

        model.addAttribute(LIST_ACTIVE, getActiveList());
        model.addAttribute("form", form);

        return "customer/viewCustomer";
    }

    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable("id") Long customerId) {

        customerService.delete(customerId);

        return REDIRECT_CUSTOMER_LIST;
    }
}
