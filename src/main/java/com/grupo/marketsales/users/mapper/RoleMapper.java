package com.grupo.marketsales.users.mapper;

import com.grupo.marketsales.users.dto.RoleDTO;
import com.grupo.marketsales.users.persistence.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDTO mapToDTO(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }
}
