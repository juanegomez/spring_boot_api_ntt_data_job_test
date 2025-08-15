package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{docType}/{docNumber}")
    public Customer getCustomer(
            @PathVariable @Pattern(regexp = "[C|P]", message = "Tipo documento debe ser C (Cédula) o P (Pasaporte)") String docType,
            @PathVariable String docNumber) {
        return customerService.getCustomerInfo(docType, docNumber);
    }
}