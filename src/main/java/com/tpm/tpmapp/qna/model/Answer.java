package com.tpm.tpmapp.qna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpm.tpmapp.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
@Setter
public class Answer {

    @Id
    @Builder.Default
    @JsonIgnore
    private UUID id = UUID.randomUUID();

    @NotBlank
    private String answerTitle;

    @NotBlank
    private String solution;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToOne
    private Question question;
}
