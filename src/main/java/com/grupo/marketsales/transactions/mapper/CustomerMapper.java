package com.grupo.marketsales.transactions.mapper;

import com.grupo.marketsales.transactions.dto.CustomerDTO;
import com.grupo.marketsales.transactions.dto.SaleDTO;
import com.grupo.marketsales.transactions.persistence.entity.Customer;
import com.grupo.marketsales.transactions.persistence.entity.Sale;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO mapToDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setDni(customer.getDni());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;

    }
}

