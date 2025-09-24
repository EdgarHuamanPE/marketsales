package com.grupo.marketsales.transactions.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RegistrationRequestCustomer {

    @NotNull
    @Size(max = 8)
    private String dni;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(max = 12)
    private String phone;

}
