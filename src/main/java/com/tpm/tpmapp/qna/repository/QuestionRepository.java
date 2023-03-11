package com.tpm.tpmapp.qna.repository;

import com.tpm.tpmapp.qna.model.Question;
import com.tpm.tpmapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findByUser(User user);
}
