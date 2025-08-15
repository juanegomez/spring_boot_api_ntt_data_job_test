package com.example.demo.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.config.CustomerMockData;
import com.example.demo.model.Customer;

class CustomerServiceTest {

    @Mock
    private CustomerMockData mockData;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockData.mockCustomers()).thenReturn(List.of(
            new Customer("23445322", "C", "Juan", "Carlos", "Pérez", "Gómez", "3001234567", "Calle 123", "Bogotá"),
            new Customer("AB12345", "P", "María", null, "Rodríguez", null, "3109876543", "Carrera 56", "Medellín")
        ));
    }

    @Test
    void cuandoClienteExiste_retornaCliente() {
        Customer result = customerService.getCustomerInfo("C", "23445322");
        assertEquals("Juan", result.getPrimerNombre());
        assertEquals("Pérez", result.getPrimerApellido());
    }

    @Test
    void cuandoClienteNoExiste_lanzaExcepcion404() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerInfo("C", "99999999");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void cuandoTipoDocumentoInvalido_lanzaExcepcion400() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.getCustomerInfo("X", "23445322");
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}