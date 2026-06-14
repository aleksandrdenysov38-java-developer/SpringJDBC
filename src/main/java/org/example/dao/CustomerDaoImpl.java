package org.example.dao;

import org.example.mapper.CustomerRowMapper;
import org.example.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Customer customer) {

        String sql =
                "INSERT INTO customer(full_name,email,social_security_number) VALUES(?,?,?)";

        jdbcTemplate.update(
                sql,
                customer.getFullName(),
                customer.getEmail(),
                customer.getSocialSecurityNumber()
        );
    }

    @Override
    public Customer findById(Long id) {

        String sql =
                "SELECT * FROM customer WHERE id=?";

        return jdbcTemplate.queryForObject(
                sql,
                new CustomerRowMapper(),
                id
        );
    }

    @Override
    public List<Customer> findAll() {

        String sql =
                "SELECT * FROM customer";

        return jdbcTemplate.query(
                sql,
                new CustomerRowMapper()
        );
    }

    @Override
    public void update(Customer customer) {

        String sql =
                "UPDATE customer " +
                        "SET full_name=?, email=?, social_security_number=? " +
                        "WHERE id=?";

        jdbcTemplate.update(
                sql,
                customer.getFullName(),
                customer.getEmail(),
                customer.getSocialSecurityNumber(),
                customer.getId()
        );
    }

    @Override
    public void delete(Long id) {

        String sql =
                "DELETE FROM customer WHERE id=?";

        jdbcTemplate.update(sql, id);
    }
}
