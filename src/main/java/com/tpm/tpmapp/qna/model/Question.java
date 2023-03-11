package com.tpm.tpmapp.qna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpm.tpmapp.user.model.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
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
public class Question {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @NotBlank
    @JsonProperty("question")
    private String questionString;

    @NotBlank
    private String title;

    @JsonIgnore
    @Builder.Default
    private Date postedAt = new Date();



}
