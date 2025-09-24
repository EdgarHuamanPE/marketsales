package com.grupo.marketsales.transactions.controller;

import com.grupo.marketsales.transactions.dto.RegistrationRequestSales;
import com.grupo.marketsales.transactions.dto.SaleDTO;
import com.grupo.marketsales.transactions.service.SaleService;

import com.grupo.marketsales.users.dto.RegistrationRequest;
import com.grupo.marketsales.users.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sales", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Integer id) {
        SaleDTO sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }


    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllUsers() {
        List<SaleDTO> sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerSale(
            @RequestBody @Valid RegistrationRequestSales registrationRequestSales
    ) {
        saleService.register(registrationRequestSales);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateSale(
            @PathVariable Integer id,
            @RequestBody @Valid RegistrationRequestSales updateRequest
    ){
        saleService.updateSale(id,updateRequest);
        return  ResponseEntity.ok(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
