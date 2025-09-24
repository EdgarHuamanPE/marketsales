package com.grupo.marketsales.transactions.controller;

import com.grupo.marketsales.transactions.dto.CustomerDTO;
import com.grupo.marketsales.transactions.dto.RegistrationRequestCustomer;
import com.grupo.marketsales.transactions.dto.RegistrationRequestSales;
import com.grupo.marketsales.transactions.dto.SaleDTO;
import com.grupo.marketsales.transactions.service.CustomerService;
import com.grupo.marketsales.transactions.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        CustomerDTO customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }


    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customer = customerService.findAll();
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerCustomer(
            @RequestBody @Valid RegistrationRequestCustomer registrationRequestCustomer
    ) {
        customerService.register(registrationRequestCustomer);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateCustomer(
            @PathVariable Integer id,
            @RequestBody @Valid RegistrationRequestCustomer updateRequest
    ){
        customerService.updateCustomer(id,updateRequest);
        return  ResponseEntity.ok(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCutomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
