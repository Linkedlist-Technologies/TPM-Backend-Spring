package com.tpm.tpmapp.user.service;

import com.tpm.tpmapp.helper.Constraints;
import com.tpm.tpmapp.user.dto.request.ChangeDeviceDTO;
import com.tpm.tpmapp.user.dto.request.LoginDTO;
import com.tpm.tpmapp.user.dto.response.RegistrationResponseDTO;
import com.tpm.tpmapp.user.model.User;
import com.tpm.tpmapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository userRepository;

    public RegistrationResponseDTO login(LoginDTO loginDTO) {

        String token = registrationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return RegistrationResponseDTO.builder()
                .status(Constraints.HTTPSTATUS_SUCCESS)
                .data(userRepository.findByEmailId(loginDTO.getUsername()).orElse(null))
                .token(token)
                .build();
    }

    public RegistrationResponseDTO changePassword(LoginDTO loginDTO) {

        String token = registrationService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return RegistrationResponseDTO.builder()
                .status(Constraints.HTTPSTATUS_FAILED)
                .data(userRepository.findByEmailId(loginDTO.getUsername()).orElse(null))
                .token(token)
                .build();
    }

    public void changeDevice(ChangeDeviceDTO changeDeviceDTO) {
        User user = this.getUserByEmail(changeDeviceDTO.getEmail());
        if (user.getMobileDevice() == null) {
            user = user.withMobileDevice(null).withWebDevice(changeDeviceDTO.getNewDevice());
        } else {
            user = user.withWebDevice(null).withMobileDevice(changeDeviceDTO.getNewDevice());
        }
        userRepository.save(user);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmailId(email).orElseThrow(() -> new UsernameNotFoundException
                (String.format("User With email %s Not Found", email)));
    }

    public boolean verifyDevice(User user, String deviceMobile) {
        if (user.getMobileDevice().equals(deviceMobile)) {
            return true;
        } else return user.getWebDevice().equals(deviceMobile);
    }
}
