package com.tpm.tpmapp.user.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    @Email(message = "Enter a Valid Email")
    private String emailId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Column(unique = true)
    @Pattern(regexp = "^[0-9]*$", message = "Enter only digits")
    private String mobile;

    private String address;

    private String webDevice;

    private String mobileDevice;

    private boolean freeUser = true;

    private int subscriptionType;

    @NotBlank
    private String password;
}
