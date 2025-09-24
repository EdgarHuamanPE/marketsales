package com.grupo.marketsales.transactions.persistence.entity;


import com.grupo.marketsales.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name="customers"
)

@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dni;
    private String firstName ;
    private String lastName;
    private String email;
    private String phone;

    public Customer() {
    }

    public Customer(Integer id, String dni, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

}
