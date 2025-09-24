package com.grupo.marketsales.transactions.dto;

import com.grupo.marketsales.users.persistence.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RegistrationRequestSales {

    @NotNull
    @Size(max = 72)
    private String salesSerial;

    @NotNull
    private BigDecimal total;

    @NotNull
    private BigDecimal igv;

    @NotNull
    private Integer customerId;

    @NotNull
    private List<SaleDetailDTO> saleDetails;


}
