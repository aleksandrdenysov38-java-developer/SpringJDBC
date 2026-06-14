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

        customerDao.save(customer);

        List<Customer> customers =
                customerDao.findAll();

        System.out.println("ALL CUSTOMERS:");

        customers.forEach(System.out::println);

        Customer found =
                customerDao.findById(1L);

        System.out.println("\nFOUND:");

        System.out.println(found);

        found.setFullName("Updated John");

        customerDao.update(found);

        System.out.println("\nAFTER UPDATE:");

        System.out.println(
                customerDao.findById(1L)
        );

        customerDao.delete(1L);

        System.out.println(
                "\nCUSTOMER DELETED"
        );

        context.close();
    }
}
