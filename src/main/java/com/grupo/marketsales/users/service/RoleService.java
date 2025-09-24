package com.grupo.marketsales.users.service;

import com.grupo.marketsales.exception.NotFoundException;
import com.grupo.marketsales.users.dto.RoleDTO;
import com.grupo.marketsales.users.dto.RoleRequestDTO;
import com.grupo.marketsales.users.mapper.RoleMapper;
import com.grupo.marketsales.users.persistence.entity.Role;
import com.grupo.marketsales.users.persistence.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RoleService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Integer id) {
        log.info("Finding role by ID: {}", id);
        return roleRepository.findById(id)
                .map(roleMapper::mapToDTO)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        log.info("Finding all roles");
        return roleRepository.findAll().stream()
                .map(roleMapper::mapToDTO)
                .toList();
    }

    public void saveRole(RoleRequestDTO roleRequestDTO) {
        log.info("Saving new role: {}", roleRequestDTO.getName());
        Role role = new Role();
        role.setName(roleRequestDTO.getName());
        role.setDescription(roleRequestDTO.getDescription());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    public void updateRole(Integer id, RoleRequestDTO roleRequestDTO) {
        log.info("Updating role with ID: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));
        role.setName(roleRequestDTO.getName());
        role.setDescription(roleRequestDTO.getDescription());
        role.setUpdatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    public void deleteRole(Integer id) {
        log.info("Deleting role with ID: {}", id);
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}
