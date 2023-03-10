package com.tpm.tpmapp.payment.service;

import com.tpm.tpmapp.payment.model.Payment;
import com.tpm.tpmapp.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void addPayment(Payment payment){
        this.paymentRepository.save(payment);
    }

}
