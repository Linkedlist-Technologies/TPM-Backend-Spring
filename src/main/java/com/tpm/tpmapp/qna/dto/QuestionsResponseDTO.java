package com.tpm.tpmapp.qna.dto;

import com.tpm.tpmapp.qna.model.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionsResponseDTO {
    private String status;

    private List<Question> data;

}
