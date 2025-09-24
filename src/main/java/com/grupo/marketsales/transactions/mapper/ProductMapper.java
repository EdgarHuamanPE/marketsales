package com.grupo.marketsales.transactions.mapper;

import com.grupo.marketsales.transactions.dto.CustomerDTO;
import com.grupo.marketsales.transactions.dto.ProductDTO;
import com.grupo.marketsales.transactions.persistence.entity.Customer;
import com.grupo.marketsales.transactions.persistence.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public ProductDTO mapToDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setStatus(product.isStatus());

        return productDTO;

    }
}
