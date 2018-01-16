package org.ogin.dao;

import org.ogin.entity.Customer;

import java.util.List;

/**
 * @author ogin
 */
public interface CustomerDAO {
    public Customer addCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

    public Customer getCustomer(long customerId);

    public List<Customer> getCustomers();
}
