package com.grupo.marketsales.users.service;


import com.grupo.marketsales.exception.NotFoundException;
import com.grupo.marketsales.users.dto.RegistrationRequest;
import com.grupo.marketsales.users.dto.UserDTO;
import com.grupo.marketsales.users.mapper.UserMapper;
import com.grupo.marketsales.users.persistence.entity.Role;
import com.grupo.marketsales.users.persistence.entity.User;
import com.grupo.marketsales.users.persistence.repository.RoleRepository;
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
@Slf4j // logging
@Transactional(rollbackFor = Exception.class)

public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper , RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.userMapper=userMapper;
        this.roleRepository=roleRepository;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Integer id){
        log.info("Encontrando user por id:{}",id);
        return  userRepository.findById(id)
                .map(userMapper::mapToDTO)
                .orElseThrow(()->{
                    return new NotFoundException("User not found");
                });
    }

    @Transactional
    public void saveUser(User user){
        log.info("Saving user: {}", user.getUsername());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.info("Finding all users");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToDTO)
                .toList();
    }

    public void register(@Valid RegistrationRequest registrationRequest) {
        log.info("Registering user with email: {}", registrationRequest.getEmail());


        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + registrationRequest.getEmail());
        }

        final User user = new User();
        user.setEmail(registrationRequest.getEmail());
        String username = registrationRequest.getFirstName().toLowerCase() + "." +
                registrationRequest.getLastName().toLowerCase(); // Andre Gallegos andre.gallegos
        user.setUsername(username);
        user.setPasswordHash(registrationRequest.getPassword());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPhone(registrationRequest.getPhone());
        user.setProfileImageUrl(registrationRequest.getProfileImageUrl());
        user.setActive(true);
        user.setVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Asignar role cliente por defecto
        Role defaultRole = roleRepository.findById(2)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + 3));

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        // Guardar usuario
        userRepository.save(user);
    }

    public void updateUser(Integer id, @Valid RegistrationRequest updateRequest ){

        log.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        user.setEmail(updateRequest.getEmail());
        user.setPasswordHash(updateRequest.getPassword());
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setPhone(updateRequest.getPhone());
        user.setProfileImageUrl(updateRequest.getProfileImageUrl());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        userRepository.delete(userToDelete);
    }

}
