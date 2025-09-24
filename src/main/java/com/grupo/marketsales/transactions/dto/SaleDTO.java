package com.grupo.marketsales.transactions.dto;

import com.grupo.marketsales.users.dto.RoleDTO;
import com.grupo.marketsales.users.dto.UserDTO;
import com.grupo.marketsales.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class SaleDTO {

    private Integer id;
    private String salesSerial;
    private BigDecimal total;
    private LocalDateTime saleDateTime;
    private BigDecimal igv;
    private UserDTO user;
    private CustomerDTO customer;
    private List<SaleDetailDTO> saleDetails;

}
