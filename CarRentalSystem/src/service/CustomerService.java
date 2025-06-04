package service;

import entity.Customer;

import java.util.*;

public class CustomerService {
    private final Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Customer getCustomer(String id) {
        return customers.get(id);
    }
}
