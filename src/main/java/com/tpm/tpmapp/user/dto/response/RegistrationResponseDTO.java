package com.tpm.tpmapp.user.dto.response;

import com.tpm.tpmapp.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponseDTO {

    private String status;
    private User data;

    private String token;
}
