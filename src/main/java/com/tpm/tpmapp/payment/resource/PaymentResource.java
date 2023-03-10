package com.tpm.tpmapp.payment.resource;

import com.tpm.tpmapp.payment.service.PaymentService;
import com.tpm.tpmapp.user.dto.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

//    @PostMapping("/payment")
//    public ResponseEntity<Object> addPayment(){
//        try {
//            userService.changeDevice(changeDeviceDTO);
//            return ResponseEntity.ok(new BaseResponse("success", "Password successfully changed"));
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body("An Internal Server Error Occured");
//        }
//    }
}
