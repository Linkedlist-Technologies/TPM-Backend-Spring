package com.tpm.tpmapp.user.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordDTO {

    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Password cant be blank")
    @Size(min = 6,message = "Password length should be minimum 6 ")
    private String newPassword;
}
