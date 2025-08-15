package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Customer;

@Configuration
public class CustomerMockData {

    @Bean
    public List<Customer> mockCustomers() {
        return Arrays.asList(
                new Customer("23445322", "C", "Juan", "Carlos", "Pérez", "Gómez", "3001234567", "Calle 123 #45-67", "Bogotá")
        );
    }
}
