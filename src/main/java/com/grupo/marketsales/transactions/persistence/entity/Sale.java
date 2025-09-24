package com.grupo.marketsales.transactions.persistence.entity;


import com.grupo.marketsales.users.persistence.entity.Role;
import com.grupo.marketsales.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name="sales"
)

@Setter
@Getter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String salesSerial;
    private BigDecimal total;
    private LocalDateTime saleDateTime;
    private BigDecimal igv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SaleDetail> saleDetails = new HashSet<>();



    public Sale() {
    }

    public Sale(Integer id, String salesSerial, BigDecimal total, LocalDateTime saleDateTime, BigDecimal igv, User user, Customer customer, Set<SaleDetail> saleDetails) {
        this.id = id;
        this.salesSerial = salesSerial;
        this.total = total;
        this.saleDateTime = saleDateTime;
        this.igv = igv;
        this.user = user;
        this.customer = customer;
        this.saleDetails = saleDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return id != null && id.equals(sale.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
