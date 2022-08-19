package com.instaKG.answerComment.service;

import com.instaKG.answer.domain.Answer;
import com.instaKG.answerComment.dao.AnswerCommentRepository;
import com.instaKG.answerComment.domain.AnswerComment;
import com.instaKG.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerCommentService {
    private final AnswerCommentRepository answerCommentRepository;

    public void create(Answer answer, Board board, String content){
        AnswerComment answerComment = new AnswerComment();
        answerComment.setContent(content);
        answerComment.setCreateDate(LocalDateTime.now());
        answerComment.setAnswer(answer);
        answerComment.setBoard(board);
        answerComment.setReplyLike(0);
        this.answerCommentRepository.save(answerComment);
    }
}
