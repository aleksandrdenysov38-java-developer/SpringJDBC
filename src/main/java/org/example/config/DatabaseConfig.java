package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds =
                new DriverManagerDataSource();

        ds.setDriverClassName(
                "org.postgresql.Driver");

        ds.setUrl(
                "jdbc:postgresql://localhost:5432/springjdbcdb");

        ds.setUsername("postgres");

        ds.setPassword("postgres");

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(
            DataSource dataSource) {

        return new JdbcTemplate(dataSource);
    }
}
