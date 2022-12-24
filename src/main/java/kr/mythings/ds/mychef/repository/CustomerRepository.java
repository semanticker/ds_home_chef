package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Customer;
import kr.mythings.ds.mychef.domain.Food;
import kr.mythings.ds.mychef.form.CustomerRatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    public List<Customer> findAll() {

        String query = "select c from Customer c";

        return em.createQuery(query, Customer.class)
                .getResultList();
    }

    private final EntityManager em;

    public Customer findOne(Long customerId) {
        return em.find(Customer.class, customerId);
    }

    public void add(Customer customer) {
        em.persist(customer);
    }

    public void delete(Long customerId) {
        em.remove(em.find(Customer.class, customerId));
    }


}
