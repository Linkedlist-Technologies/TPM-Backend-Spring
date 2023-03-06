package com.tpm.tpmapp.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;
}
