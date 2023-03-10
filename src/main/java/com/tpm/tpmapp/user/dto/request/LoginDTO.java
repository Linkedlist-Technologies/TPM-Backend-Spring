package com.tpm.tpmapp.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "Enter a valid UserName")
    private String username;

    @NotBlank(message = "Enter a valid Password")
    private String password;

    private String deviceMobile;
}
