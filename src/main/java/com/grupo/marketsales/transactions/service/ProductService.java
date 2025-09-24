package com.grupo.marketsales.transactions.service;


import com.grupo.marketsales.exception.NotFoundException;
import com.grupo.marketsales.transactions.dto.ProductDTO;
import com.grupo.marketsales.transactions.dto.RegistrationRequestProduct;
import com.grupo.marketsales.transactions.mapper.ProductMapper;
import com.grupo.marketsales.transactions.persistence.entity.Product;
import com.grupo.marketsales.transactions.persistence.repository.ProductRepository;
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
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Integer id) {
        log.info("Finding product by ID: {}", id);
        return productRepository.findById(id)
                .map(productMapper::mapToDTO)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
    }

    @Transactional
    public void saveProduct(Product product){
        log.info("Saving customer: {}", product.getId());
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        log.info("Finding all Product");
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::mapToDTO)
                .toList();
    }

    public void register(@Valid RegistrationRequestProduct registrationRequestProduct) {
        log.info("Registering Product  : {}", registrationRequestProduct.getName());


        if (productRepository.existsByName(registrationRequestProduct.getName())) {
            throw new IllegalArgumentException("Product already in use: " + registrationRequestProduct.getName());
        }

        final Product product = new Product();
        product.setName(registrationRequestProduct.getName());
        product.setDescription(registrationRequestProduct.getDescription());
        product.setPrice(registrationRequestProduct.getPrice());
        product.setStock(registrationRequestProduct.getStock());
        product.setStatus(registrationRequestProduct.isStatus());


        // Guardar usuario
        productRepository.save(product);
    }

    public void updateProduct(Integer id, @Valid RegistrationRequestProduct updateRequestProduct ){

        log.info("Updating Product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found with ID: " + id));

        product.setName(updateRequestProduct.getName());
        product.setDescription(updateRequestProduct.getDescription());
        product.setPrice(updateRequestProduct.getPrice());
        product.setStock(updateRequestProduct.getStock());
        product.setStatus(updateRequestProduct.isStatus());
        productRepository.save(product);

    }

    public void deleteProduct(Integer id) {
        log.info("Deleting Product with ID: {}", id);

        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found with ID: " + id);
        }
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        productRepository.delete(productToDelete);
    }

}
