package com.grupo.marketsales.transactions.dto;

import com.grupo.marketsales.transactions.persistence.entity.Sale;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class RegistrationRequestSaleDetail {

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer productId;

}
