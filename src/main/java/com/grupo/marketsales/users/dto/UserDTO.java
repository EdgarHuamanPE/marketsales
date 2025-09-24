package com.grupo.marketsales.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String profileImageUrl;
    private boolean isActive;
    private boolean isVerified;
    private Set<RoleDTO> roles;

}
