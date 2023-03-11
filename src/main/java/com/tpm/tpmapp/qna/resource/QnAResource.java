package com.tpm.tpmapp.qna.resource;

import com.tpm.tpmapp.helper.Constraints;
import com.tpm.tpmapp.qna.dto.QuestionsResponseDTO;
import com.tpm.tpmapp.qna.service.QnAService;
import com.tpm.tpmapp.user.dto.response.BaseResponse;
import com.tpm.tpmapp.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@Slf4j
public class QnAResource {

    @Autowired
    private QnAService qnAService;

    @PostMapping("/questions")
    public ResponseEntity<Object> addQuestion(@AuthenticationPrincipal User user, @NotBlank @RequestParam String title, @NotBlank @RequestParam String question) {
        try {
            qnAService.addQuestion(user, title, question);
            return ResponseEntity.ok(new BaseResponse(Constraints.HTTPSTATUS_SUCCESS, "Question Added successfully changed"));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @GetMapping("/questions")
    public ResponseEntity<Object> getQuestions(@AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(QuestionsResponseDTO.builder()
                    .status(Constraints.HTTPSTATUS_SUCCESS)
                    .data(qnAService.getAllQuestions(user))
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constraints.INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }
}
