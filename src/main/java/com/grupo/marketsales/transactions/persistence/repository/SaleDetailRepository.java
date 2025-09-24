package com.grupo.marketsales.transactions.persistence.repository;

import com.grupo.marketsales.transactions.persistence.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail,Integer> {

}
