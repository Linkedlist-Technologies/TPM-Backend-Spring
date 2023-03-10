package com.tpm.tpmapp.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
@Setter
@Table
public class Payment {

    @Id
    @Builder.Default
    @JsonIgnore
    private UUID id = UUID.randomUUID();

    @Email(message = "Enter valid Email")
    private String mail;

    private UUID orderId;

    private String txnId;

    private double amount;

    private String planType;

    @Builder.Default
    private Date date = new Date();
}
