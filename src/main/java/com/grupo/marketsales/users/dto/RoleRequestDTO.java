package com.grupo.marketsales.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {
    @NotNull
    private String name;
    private String description;
}
