package com.tpm.tpmapp.user.service;

import com.tpm.tpmapp.helper.JwtUtil;
import com.tpm.tpmapp.user.dto.request.UserDTO;
import com.tpm.tpmapp.user.dto.response.RegistrationResponseDTO;
import com.tpm.tpmapp.user.model.User;
import com.tpm.tpmapp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public RegistrationResponseDTO register(UserDTO userDTO) throws Exception {
        User user = this.create(userDTO);
        String token = jwtUtil.generateToken(user.getUsername());
        return RegistrationResponseDTO.builder()
                .status("success")
                .data(user)
                .token(token)
                .build();
    }

    public User create(UserDTO userDto) throws AuthenticationException {

        if (userRepository.findByEmailId(userDto.getEmailId()).isPresent()) {
            throw new AuthenticationException("User with this EmailId Already Exist") {
            };
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
        log.info("Password : " + encodedPassword);
        userDto.setPassword(encodedPassword);
        User user = User.builder()
                .address(userDto.getAddress())
                .freeUser(userDto.isFreeUser())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .mobile(userDto.getMobile())
                .username(userDto.getEmailId())
                .emailId(userDto.getEmailId())
                .password(userDto.getPassword())
                .subscriptionType(userDto.getSubscriptionType())
                .mobile(userDto.getMobile())
                .webDevice(userDto.getWebDevice())
                .build();
        return userRepository.save(user);
    }

    public String login(String username, String password) {

        String encryptedPassword = checkPassword(password, username);
        log.info(encryptedPassword + " " + username);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encryptedPassword)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(username);
    }

    private String checkPassword(String password, String username) {
        Optional<User> userData = userRepository.findByEmailId(username);
        if (userData.isPresent()) {
            log.info("DTO Password is : {} and User Password is {}", password, userData.get().getPassword());
            if (new BCryptPasswordEncoder().matches(password, userData.get().getPassword())) {
                return userData.get().getPassword();
            } else {
                return password;
            }
        } else {
            return password;
        }

    }

    public Boolean changePassword(String password, String username) {
        Optional<User> userData = this.userRepository.findByEmailId(username);
        if (userData.isPresent()) {
            User user = userData.get();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }

    }
}
