package com.grupo.marketsales.transactions.dto;


import com.grupo.marketsales.users.dto.UserDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private boolean status;

}
