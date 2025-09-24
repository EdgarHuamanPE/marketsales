package com.grupo.marketsales.transactions.persistence.repository;

import com.grupo.marketsales.transactions.persistence.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    boolean existsBySalesSerial(String salesSerial);
}
