package com.grupo.marketsales.transactions.service;

import com.grupo.marketsales.exception.NotFoundException;
import com.grupo.marketsales.transactions.dto.RegistrationRequestSales;
import com.grupo.marketsales.transactions.dto.SaleDTO;
import com.grupo.marketsales.transactions.dto.SaleDetailDTO;
import com.grupo.marketsales.transactions.mapper.SaleMapper;
import com.grupo.marketsales.transactions.persistence.entity.Customer;
import com.grupo.marketsales.transactions.persistence.entity.Product;
import com.grupo.marketsales.transactions.persistence.entity.Sale;
import com.grupo.marketsales.transactions.persistence.entity.SaleDetail;
import com.grupo.marketsales.transactions.persistence.repository.CustomerRepository;
import com.grupo.marketsales.transactions.persistence.repository.ProductRepository;
import com.grupo.marketsales.transactions.persistence.repository.SaleRepository;

import com.grupo.marketsales.users.dto.RegistrationRequest;
import com.grupo.marketsales.users.dto.UserDTO;
import com.grupo.marketsales.users.persistence.entity.Role;
import com.grupo.marketsales.users.persistence.entity.User;
import com.grupo.marketsales.users.persistence.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SaleService {

    private static final Logger log = LoggerFactory.getLogger(SaleService.class);
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;

    public SaleService(SaleRepository saleRepository, UserRepository userRepository, CustomerRepository customerRepository, ProductRepository productRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleMapper = saleMapper;
    }

    @Transactional(readOnly = true)
    public SaleDTO findById(Integer id) {
        log.info("Finding sale by ID: {}", id);
        return saleRepository.findById(id)
                .map(saleMapper::mapToDTO)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
    }


    @Transactional
    public void saveSale(Sale sale){
        log.info("Saving user: {}", sale.getSalesSerial());
        saleRepository.save(sale);
    }

    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        log.info("Finding all sales");
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(saleMapper::mapToDTO)
                .toList();
    }

    public void register(@Valid RegistrationRequestSales registrationRequestSales) {
        log.info("Registering sales with serialsales: {}", registrationRequestSales.getSalesSerial());


        if (saleRepository.existsBySalesSerial(registrationRequestSales.getSalesSerial())) {
            throw new IllegalArgumentException("SalesSerial already in use: " + registrationRequestSales.getSalesSerial());
        }

        final Sale sale = new Sale();
        sale.setSalesSerial(registrationRequestSales.getSalesSerial());

        sale.setTotal(registrationRequestSales.getTotal());
        sale.setIgv(registrationRequestSales.getIgv());

        // Asignar personal de venta  por defecto
        User defaultUser = userRepository.findById(2)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + 2));

        sale.setUser(defaultUser);

        //Asignar cliente en la venta

        Customer customer = customerRepository.findById(registrationRequestSales.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + registrationRequestSales.getCustomerId()));

        sale.setCustomer(customer);

        if (registrationRequestSales.getSaleDetails() != null) {
            for (SaleDetailDTO detailDTO : registrationRequestSales.getSaleDetails()) {
                SaleDetail detail = new SaleDetail();
                detail.setQuantity(detailDTO.getQuantity());
                //detail.setPrice(detailDTO.getPrice());

                Product product = productRepository.findById(detailDTO.getProductId())
                        .orElseThrow(() -> new NotFoundException("Product not found with ID: " + detailDTO.getProductId()));

                detail.setProduct(product);


                // Importante: enlazar ambos lados
                detail.setSale(sale);
                sale.getSaleDetails().add(detail);
            }
        }
        // Guardar usuario
        saleRepository.save(sale);
    }

    public void updateSale(Integer id, @Valid RegistrationRequestSales updateRequestSales ){

        log.info("Updating sales with ID: {}", id);
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sale not found with ID: " + id));


        sale.setSalesSerial(updateRequestSales.getSalesSerial());
        sale.setTotal(updateRequestSales.getTotal());
        sale.setIgv(updateRequestSales.getIgv());

        saleRepository.save(sale);

    }

    public void deleteSale(Integer id) {
        log.info("Deleting Sale with ID: {}", id);
        if (!saleRepository.existsById(id)) {
            throw new NotFoundException("Sale not found with ID: " + id);
        }
        Sale saleToDelete = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        saleRepository.delete(saleToDelete);
    }

}
