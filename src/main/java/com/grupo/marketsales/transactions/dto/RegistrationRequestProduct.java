package com.grupo.marketsales.transactions.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RegistrationRequestProduct {



    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String description;

    @NotNull
    private BigDecimal price;


    @NotNull
    private Integer stock;

    @NotNull
    private boolean status;


}
