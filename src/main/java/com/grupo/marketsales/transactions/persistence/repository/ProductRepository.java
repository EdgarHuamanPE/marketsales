package com.grupo.marketsales.transactions.persistence.repository;

import com.grupo.marketsales.transactions.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product,Integer>{

    boolean existsById(Integer id);
    boolean existsByName(String name);


}
