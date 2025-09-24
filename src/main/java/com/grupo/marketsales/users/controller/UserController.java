package com.grupo.marketsales.users.controller;

import com.grupo.marketsales.users.dto.RegistrationRequest;
import com.grupo.marketsales.users.dto.UserDTO;
import com.grupo.marketsales.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users",produces = MediaType.APPLICATION_JSON_VALUE)

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
            @RequestBody @Valid RegistrationRequest registrationRequest
    ) {
        userService.register(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUser(
            @PathVariable Integer id,
            @RequestBody @Valid RegistrationRequest updateRequest
    ){
        userService.updateUser(id,updateRequest);
        return  ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
