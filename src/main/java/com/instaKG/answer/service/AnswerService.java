package com.instaKG.answer.service;

import com.instaKG.answer.dao.AnswerRepository;
import com.instaKG.answer.domain.Answer;
import com.instaKG.board.domain.Board;
import com.instaKG.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer getAnswer(Long id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        }else{
            throw new DataNotFoundException("board not found");
        }
    }

    public void create(Board board, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setBoard(board);
        answer.setReplyLike(false);
        this.answerRepository.save(answer);
    }

    public void modify(Answer answer, String content, Boolean onOff){
        answer.setContent(content);
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    public void setReplyLike(Long answerId){
        Answer answer = answerRepository.findById(answerId).get();
        if(answer.getReplyLike() == true){
            answer.setReplyLike(false);
        }else{
            answer.setReplyLike(true);
        }
        this.answerRepository.save(answer);
    }
}
