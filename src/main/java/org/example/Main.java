package org.example;

import org.example.config.AppConfig;
import org.example.dao.CustomerDao;
import org.example.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfig.class);

        CustomerDao customerDao =
                context.getBean(CustomerDao.class);

        Customer customer =
                new Customer(
                        null,
                        "John Smith",
                        "john@gmail.com",
                        "123456789"
                );

        customer = customerDao.save(customer);

        System.out.println(
                "Generated ID = "
                        + customer.getId()
        );

        Customer found =
                customerDao.findById(
                        customer.getId()
                );

        System.out.println(found);

        found.setFullName(
                "Updated John Smith"
        );

        customerDao.update(found);

        List<Customer> customers =
                customerDao.findAll();

        customers.forEach(
                System.out::println
        );

        customerDao.delete(
                customer.getId()
        );

        context.close();
    }
}
