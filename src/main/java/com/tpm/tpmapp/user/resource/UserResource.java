package com.tpm.tpmapp.user.resource;

import com.tpm.tpmapp.user.dto.request.ChangeDeviceDTO;
import com.tpm.tpmapp.user.dto.response.BaseResponse;
import com.tpm.tpmapp.user.dto.request.ChangePasswordDTO;
import com.tpm.tpmapp.user.dto.request.LoginDTO;
import com.tpm.tpmapp.user.dto.request.UserDTO;
import com.tpm.tpmapp.user.model.User;
import com.tpm.tpmapp.user.service.RegistrationService;
import com.tpm.tpmapp.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping("/user")
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
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An Internal Server Error Occured");
        }

    }

    @PostMapping("/mlogin")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(userService.login(loginDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An Internal Server Error Occured");
        }

    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody ChangePasswordDTO request) {
        try {
            if (registrationService.changePassword(request.getNewPassword(), user.getEmailId())) {
                return ResponseEntity.ok(new BaseResponse("success", "Password successfully changed"));
            } else {
                return ResponseEntity.ok(new BaseResponse("failure", "User does not exists"));
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An Internal Server Error Occured");
        }
    }

    @PutMapping("/changeDevice")
    public ResponseEntity<Object> changeDevice(@Valid @RequestBody ChangeDeviceDTO changeDeviceDTO){
        try {
           userService.changeDevice(changeDeviceDTO);
            return ResponseEntity.ok(new BaseResponse("success", "Password successfully changed"));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An Internal Server Error Occured");
        }
        return null;
    }

}
