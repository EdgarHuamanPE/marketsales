package com.grupo.marketsales.transactions.mapper;

import com.grupo.marketsales.transactions.dto.CustomerDTO;
import com.grupo.marketsales.transactions.dto.SaleDTO;
import com.grupo.marketsales.transactions.dto.SaleDetailDTO;
import com.grupo.marketsales.transactions.persistence.entity.Customer;
import com.grupo.marketsales.transactions.persistence.entity.Sale;
import com.grupo.marketsales.users.dto.RoleDTO;
import com.grupo.marketsales.users.dto.UserDTO;
import com.grupo.marketsales.users.persistence.entity.Role;
import com.grupo.marketsales.users.persistence.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SaleMapper {
    public SaleDTO mapToDTO(Sale sale) {
        if (sale == null) {
            return null;
        }

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setSalesSerial(sale.getSalesSerial());
        saleDTO.setTotal(sale.getTotal());
        saleDTO.setSaleDateTime(sale.getSaleDateTime());
        saleDTO.setIgv(sale.getIgv());

        if (sale.getUser() != null) {
            User user = sale.getUser();

            UserDTO userDTO = new UserDTO();
            //userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setPhone(user.getPhone());
            userDTO.setProfileImageUrl(user.getProfileImageUrl());

            saleDTO.setUser(userDTO);

            if (user.getRoles() != null) {
                Set<RoleDTO> roleDTOs = user.getRoles().stream()
                        .map(role -> {
                            RoleDTO roleDTO = new RoleDTO();
                            roleDTO.setId(role.getId());
                            roleDTO.setName(role.getName());
                            return roleDTO;
                        })
                        .collect(Collectors.toSet());
                userDTO.setRoles(roleDTOs);
            }
        }

        if(sale.getCustomer() != null){
            Customer customer = sale.getCustomer();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setDni(customer.getDni());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setPhone(customer.getPhone());

            saleDTO.setCustomer(customerDTO);
        }

        if (sale.getSaleDetails() != null) {
                List<SaleDetailDTO> saleDetailDTOs = sale.getSaleDetails().stream()
                        .map(detail -> {
                            SaleDetailDTO saleDetailDTO = new SaleDetailDTO();
                            saleDetailDTO.setId(detail.getId());
                            saleDetailDTO.setQuantity(detail.getQuantity());
                            //saleDetailDTO.setPrice(detail.getProduct().getPrice());
                            saleDetailDTO.setProductId(detail.getProduct().getId());
                            return saleDetailDTO;

                        })
                        .collect(Collectors.toList());
            saleDTO.setSaleDetails(saleDetailDTOs);
        }


        return saleDTO;
    }
}
