package com.grupo.marketsales.transactions.controller;


import com.grupo.marketsales.transactions.dto.CustomerDTO;
import com.grupo.marketsales.transactions.dto.ProductDTO;
import com.grupo.marketsales.transactions.dto.RegistrationRequestCustomer;
import com.grupo.marketsales.transactions.dto.RegistrationRequestProduct;
import com.grupo.marketsales.transactions.service.CustomerService;
import com.grupo.marketsales.transactions.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerProduct(
            @RequestBody @Valid RegistrationRequestProduct registrationRequestProduct
    ) {
        productService.register(registrationRequestProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateProduct(
            @PathVariable Integer id,
            @RequestBody @Valid RegistrationRequestProduct  updateRequest
    ){
        productService.updateProduct(id,updateRequest);
        return  ResponseEntity.ok(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
