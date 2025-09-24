package com.grupo.marketsales.transactions.dto;

import com.grupo.marketsales.transactions.persistence.entity.Sale;
import com.grupo.marketsales.users.dto.UserDTO;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class SaleDetailDTO {

    private Integer id;
    private Integer quantity;
    //private BigDecimal price;
    private Integer productId;


}
