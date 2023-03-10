package com.tpm.tpmapp.user.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ChangeDeviceDTO {

    @NotBlank(message = "new Device can't be blank")
    private String newDevice;

    @Email(message = "Enter a valid email address")
    private String email;
}
