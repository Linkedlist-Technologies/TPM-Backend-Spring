package com.tpm.tpmapp.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeDeviceDTO {

    @NotBlank
    private String newDevice;

    @NotBlank
    private String email;
}
