package org.mihaiaposotlproiectfinal;

import org.mihaiaposotlproiectfinal.jdbcThymeleaf.controller.ClientController;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories.ClientDao;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories.ClientDaoImpl;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.service.ClientService;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.service.ClientServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@SpringBootApplication
@Configuration
public class OnlineStore {

    @Bean
    ClientService clientService(){
        return new ClientServiceImpl(clientDaoImpl());
    }

    @Bean
    ClientDao clientDaoImpl() {
        return new ClientDaoImpl(new NamedParameterJdbcTemplate(mysqlDataSource()));
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/project");
        dataSource.setUsername("siit");
        dataSource.setPassword("siit");
        return dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineStore.class, args);
    }

}
