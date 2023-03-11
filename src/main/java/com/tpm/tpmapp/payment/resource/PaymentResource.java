package com.tpm.tpmapp.payment.resource;

import com.tpm.tpmapp.helper.Constraints;
import com.tpm.tpmapp.payment.model.Payment;
import com.tpm.tpmapp.payment.service.PaymentService;
import com.tpm.tpmapp.user.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")

    public ResponseEntity<Object> addPayment(@Valid @RequestBody Payment payment){
        try {
            paymentService.addPayment(payment);
            return ResponseEntity.ok(new BaseResponse(Constraints.HTTPSTATUS_SUCCESS, "Payment Added successfully changed"));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }
}
