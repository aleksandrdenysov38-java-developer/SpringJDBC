package org.example.dao;

import org.example.model.Customer;

import java.util.List;

public interface CustomerDao {

    void save(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void update(Customer customer);

    void delete(Long id);
}
