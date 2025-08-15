package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Customer {

    @JsonProperty("numeroDocumento")
    private String numeroDocumento;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("primerNombre")
    private String primerNombre;

    @JsonProperty("segundoNombre")
    private String segundoNombre;

    @JsonProperty("primerApellido")
    private String primerApellido;

    @JsonProperty("segundoApellido")
    private String segundoApellido;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("ciudad")
    private String ciudad;

    public Customer(String numeroDocumento, String tipoDocumento,
            String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido,
            String telefono, String direccion, String ciudad) {
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }
}
