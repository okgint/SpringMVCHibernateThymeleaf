package org.ogin.services;

import org.ogin.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ogin
 */
public interface CustomerService {
    public Customer addCustomer(String customerName, String country);

    public Customer updateCustomer(long customerId, String customerName, String country);

    public Customer getCustomer(long customerId);

    public List<Customer> getAllCustomers();
}
