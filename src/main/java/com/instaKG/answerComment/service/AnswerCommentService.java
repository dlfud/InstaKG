package com.instaKG.answerComment.service;

import com.instaKG.answer.domain.Answer;
import com.instaKG.answerComment.dao.AnswerCommentRepository;
import com.instaKG.answerComment.domain.AnswerComment;
import com.instaKG.board.domain.Board;
import com.instaKG.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public void setLike(Long answerCommentsId) {
        AnswerComment answerComment = answerCommentRepository.findById(answerCommentsId).get();
        if(answerComment.getReplyLike()==1) {
            answerComment.setReplyLike(0);
        } else {
            answerComment.setReplyLike(1);
        }
        this.answerCommentRepository.save(answerComment);
    }

    public AnswerComment getAnswerComment(Long id){
        Optional<AnswerComment> answerComment = this.answerCommentRepository.findById(id);
        if (answerComment.isPresent()) {
            return answerComment.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void delete(AnswerComment answerComment) {
        this.answerCommentRepository.delete(answerComment);
    }

    public void modify(AnswerComment answerComment, String content) {
        answerComment.setContent(content);
        answerComment.setModifyDate(LocalDateTime.now());
        this.answerCommentRepository.save(answerComment);
    }
}