package com.grupo.marketsales.transactions.persistence.repository;

import com.grupo.marketsales.transactions.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {

    boolean existsById(Integer id);
    boolean existsByDni(String dni);
}
