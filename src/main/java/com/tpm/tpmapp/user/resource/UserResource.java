package com.tpm.tpmapp.user.resource;

import com.tpm.tpmapp.helper.Constraints;
import com.tpm.tpmapp.user.dto.request.ChangeDeviceDTO;
import com.tpm.tpmapp.user.dto.request.ChangePasswordDTO;
import com.tpm.tpmapp.user.dto.request.LoginDTO;
import com.tpm.tpmapp.user.dto.request.UserDTO;
import com.tpm.tpmapp.user.dto.response.BaseResponse;
import com.tpm.tpmapp.user.model.User;
import com.tpm.tpmapp.user.service.RegistrationService;
import com.tpm.tpmapp.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserResource {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(registrationService.register(userDTO));
        } catch(AuthenticationException e){
            return ResponseEntity.ok().body(new BaseResponse("failed","User already exist :("));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }

    }

    @PostMapping("/mlogin")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(userService.login(loginDTO));
        } catch(BadCredentialsException exception){
            return ResponseEntity.ok().body(new BaseResponse("failed","UserName or Password is invalid :("));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }

    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody ChangePasswordDTO request) {
        try {
            if (Boolean.TRUE.equals(registrationService.changePassword(request.getNewPassword(), user.getEmailId()))) {
                return ResponseEntity.ok(new BaseResponse(Constraints.HTTPSTATUS_SUCCESS, "Password successfully changed"));
            } else {
                return ResponseEntity.ok(new BaseResponse(Constraints.HTTPSTATUS_SUCCESS, "User does not exists"));
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @PutMapping("/changeDevice")
    public ResponseEntity<Object> changeDevice(@Valid @RequestBody ChangeDeviceDTO changeDeviceDTO) {
        try {
            userService.changeDevice(changeDeviceDTO);
            return ResponseEntity.ok(new BaseResponse(Constraints.HTTPSTATUS_SUCCESS, "Password successfully changed"));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

}
