package com.grupo.marketsales.transactions.service;


import com.grupo.marketsales.exception.NotFoundException;
import com.grupo.marketsales.transactions.dto.CustomerDTO;
import com.grupo.marketsales.transactions.dto.RegistrationRequestCustomer;
import com.grupo.marketsales.transactions.dto.RegistrationRequestSales;
import com.grupo.marketsales.transactions.dto.SaleDTO;
import com.grupo.marketsales.transactions.mapper.CustomerMapper;
import com.grupo.marketsales.transactions.mapper.SaleMapper;
import com.grupo.marketsales.transactions.persistence.entity.Customer;
import com.grupo.marketsales.transactions.persistence.entity.Sale;
import com.grupo.marketsales.transactions.persistence.repository.CustomerRepository;
import com.grupo.marketsales.transactions.persistence.repository.SaleRepository;
import com.grupo.marketsales.users.persistence.entity.User;
import com.grupo.marketsales.users.persistence.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional(readOnly = true)
    public CustomerDTO findById(Integer id) {
        log.info("Finding customer by ID: {}", id);
        return customerRepository.findById(id)
                .map(customerMapper::mapToDTO)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));
    }

    @Transactional
    public void saveCustomer(Customer customer){
        log.info("Saving customer: {}", customer.getDni());
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll() {
        log.info("Finding all customer");
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::mapToDTO)
                .toList();
    }

    public void register(@Valid RegistrationRequestCustomer registrationRequestCustomer) {
        log.info("Registering sales with serialsales: {}", registrationRequestCustomer.getDni());


        if (customerRepository.existsByDni(registrationRequestCustomer.getDni())) {
            throw new IllegalArgumentException("SalesSerial already in use: " + registrationRequestCustomer.getDni());
        }

        final Customer customer = new Customer();
        customer.setDni(registrationRequestCustomer.getDni());
        customer.setLastName(registrationRequestCustomer.getLastName());
        customer.setFirstName(registrationRequestCustomer.getFirstName());
        customer.setEmail(registrationRequestCustomer.getEmail());
        customer.setPhone(registrationRequestCustomer.getPhone());


        // Guardar usuario
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id, @Valid RegistrationRequestCustomer updateRequestCustomer ){

        log.info("Updating customer with ID: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("customer not found with ID: " + id));

        customer.setDni(updateRequestCustomer.getDni());
        customer.setLastName(updateRequestCustomer.getLastName());
        customer.setFirstName(updateRequestCustomer.getFirstName());
        customer.setEmail(updateRequestCustomer.getEmail());
        customer.setPhone(updateRequestCustomer.getPhone());
        customerRepository.save(customer);

    }

    public void deleteCustomer(Integer id) {
        log.info("Deleting Customer with ID: {}", id);

        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer not found with ID: " + id);
        }
        Customer customerToDelete = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + id));
        customerRepository.delete(customerToDelete);
    }




}
