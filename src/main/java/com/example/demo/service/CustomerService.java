package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.config.CustomerMockData;
import com.example.demo.model.Customer;

@Service
public class CustomerService {

    private final List<Customer> mockCustomers;

    @Autowired
    public CustomerService(CustomerMockData mockData) {
        this.mockCustomers = mockData.mockCustomers();
    }

    public Customer getCustomerInfo(String docType, String docNumber) {
        validateDocumentInput(docType, docNumber);
        return findCustomer(docType, docNumber)
                .orElseThrow(() -> notFoundException(docType, docNumber));
    }

    private void validateDocumentInput(String docType, String docNumber) {
        // Validación de tipo documento
        if (!"C".equalsIgnoreCase(docType) && !"P".equalsIgnoreCase(docType)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Tipo documento inválido. Use C (Cédula) o P (Pasaporte)"
            );
        }

        // Validación específica por tipo
        if ("C".equalsIgnoreCase(docType)) {
            if (docNumber == null || !docNumber.matches("^\\d{6,12}$")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "La cédula debe contener entre 6 y 12 dígitos numéricos"
                );
            }
        } else { // Para pasaportes
            if (docNumber == null || !docNumber.matches("^[a-zA-Z0-9]{6,12}$")) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "El pasaporte debe contener entre 6 y 12 caracteres alfanuméricos (letras y/o números)"
                );
            }
        }
    }

    private Optional<Customer> findCustomer(String docType, String docNumber) {
        return mockCustomers.stream()
                .filter(c -> matchesDocument(c, docType, docNumber))
                .findFirst();
    }

    private boolean matchesDocument(Customer customer, String docType, String docNumber) {
        return customer.getTipoDocumento().equalsIgnoreCase(docType)
                && customer.getNumeroDocumento().equals(docNumber);
    }

    private ResponseStatusException notFoundException(String docType, String docNumber) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Cliente con %s=%s no encontrado", docType, docNumber)
        );
    }
}
