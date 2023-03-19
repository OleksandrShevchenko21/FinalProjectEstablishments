package com.finalprojectestablishments.finalprojectestablishments.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private String role;

}
