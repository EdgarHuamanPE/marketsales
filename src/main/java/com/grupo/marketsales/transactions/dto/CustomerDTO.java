package com.grupo.marketsales.transactions.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDTO {
    private Integer id;
    private String dni;
    private String firstName ;
    private String lastName;
    private String email;
    private String phone;
}
