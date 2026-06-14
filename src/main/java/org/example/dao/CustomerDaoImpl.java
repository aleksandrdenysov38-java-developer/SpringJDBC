package org.example.dao;

import org.example.mapper.CustomerRowMapper;
import org.example.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl
        implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    private final CustomerRowMapper rowMapper;

    public CustomerDaoImpl(
            JdbcTemplate jdbcTemplate,
            CustomerRowMapper rowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Customer save(Customer customer) {

        String sql =
                """
                INSERT INTO customer
                (full_name,email,social_security_number)
                VALUES (?,?,?)
                RETURNING id
                """;

        Long generatedId =
                jdbcTemplate.queryForObject(
                        sql,
                        Long.class,
                        customer.getFullName(),
                        customer.getEmail(),
                        customer.getSocialSecurityNumber()
                );

        customer.setId(generatedId);

        return customer;
    }

    @Override
    public Customer findById(Long id) {

        String sql =
                "SELECT * FROM customer WHERE id=?";

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                id
        );
    }

    @Override
    public List<Customer> findAll() {

        String sql =
                "SELECT * FROM customer";

        return jdbcTemplate.query(
                sql,
                rowMapper
        );
    }

    @Override
    public void update(Customer customer) {

        String sql =
                """
                UPDATE customer
                SET full_name=?,
                    email=?,
                    social_security_number=?
                WHERE id=?
                """;

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
