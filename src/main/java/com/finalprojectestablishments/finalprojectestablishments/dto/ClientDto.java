package com.finalprojectestablishments.finalprojectestablishments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @NotBlank
    private String clientName;
    @NotBlank
    private String password;

}
