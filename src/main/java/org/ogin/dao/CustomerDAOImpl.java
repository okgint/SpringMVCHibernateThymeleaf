package org.ogin.dao;

import org.ogin.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author ogin
 */
@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {
    @PersistenceContext
    public EntityManager entityManager;

    @Transactional(readOnly=false)
    public Customer addCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }
    @Transactional(readOnly=false)
    public Customer updateCustomer(Customer customer) {
        entityManager.merge(customer);
        return customer;
    }

    @Transactional(readOnly=false)
    public void deleteCustomer(Customer customer) {

    }

    @Transactional(readOnly=true)
    public Customer getCustomer(long customerId) {
        String sql = "SELECT customer from Customer customer where customer.customerId=" + customerId;
        return (Customer) entityManager.createQuery(sql).getSingleResult();
    }

    @Transactional(readOnly=true)
    public List<Customer> getCustomers() {
        return entityManager.createQuery("SELECT customer from Customer customer").getResultList();
    }
}
