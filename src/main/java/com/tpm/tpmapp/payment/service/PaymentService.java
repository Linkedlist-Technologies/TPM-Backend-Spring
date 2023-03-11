package com.tpm.tpmapp.payment.service;

import com.tpm.tpmapp.payment.model.Payment;
import com.tpm.tpmapp.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void addPayment(Payment payment){
        payment = payment.withId(UUID.randomUUID());
        this.paymentRepository.save(payment);
    }

}
