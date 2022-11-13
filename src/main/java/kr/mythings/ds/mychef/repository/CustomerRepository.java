package kr.mythings.ds.mychef.repository;

import kr.mythings.ds.mychef.domain.Customer;
import kr.mythings.ds.mychef.domain.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final EntityManager em;

    public List<Customer> findAll() {

        String query = "select c from Customer c";

        return em.createQuery(query, Customer.class)
                .getResultList();
    }

    public Customer findOne(Long customerId) {
        return em.find(Customer.class, customerId);
    }

    public void add(Customer customer) {
        em.persist(customer);
    }
}
