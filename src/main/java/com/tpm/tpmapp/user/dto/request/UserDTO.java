package com.tpm.tpmapp.user.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDTO {

    @Email(message = "Enter a Valid Email")
    private String emailId;

    private String firstName;

    private String lastName;

    private String mobile;

    private String address;

    private String webDevice;

    private String mobileDevice;

    private boolean freeUser = true;

    private int subscriptionType;

    @NotBlank
    private String password;
}
