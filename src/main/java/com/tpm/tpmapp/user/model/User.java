package com.tpm.tpmapp.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpm.tpmapp.payment.model.Payment;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(unique = true)
    private String emailId;

    private String firstName;

    private String lastName;

    private String mobile;

    private String address;

    private String webDevice;

    private String mobileDevice;

    @Builder.Default
    private boolean freeUser = true;

    private int subscriptionType;

    @OneToMany
    @ToString.Exclude
    private List<Payment> payments;

    @Column(unique = true)
    private String username = null;
    @JsonIgnore
    private String password = null;
    @JsonIgnore
    @Builder.Default
    private boolean accountNonExpired = true;
    @JsonIgnore
    @Builder.Default
    private boolean accountNonLocked = true;
    @JsonIgnore
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @JsonIgnore
    @Builder.Default
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }


    public void setEmailId(String emailId) {
        this.emailId = emailId;
        this.username = emailId;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
