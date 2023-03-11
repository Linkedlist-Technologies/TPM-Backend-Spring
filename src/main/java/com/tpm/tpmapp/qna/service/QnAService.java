package com.tpm.tpmapp.qna.service;

import com.tpm.tpmapp.qna.model.Question;
import com.tpm.tpmapp.qna.repository.QuestionRepository;
import com.tpm.tpmapp.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAService {

    @Autowired
    private QuestionRepository questionRepository;

    public void addQuestion(User user, String title, String questionString) {
        Question question = Question.builder()
                .title(title)
                .user(user)
                .questionString(questionString)
                .build();
        questionRepository.save(question);
    }

    public List<Question> getAllQuestions(User user) {
        return questionRepository.findByUser(user);
    }
}
