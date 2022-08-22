package com.instaKG.answerComment.controller;

import com.instaKG.answer.domain.Answer;
import com.instaKG.answer.service.AnswerService;
import com.instaKG.answerComment.AnswerCommentForm;
import com.instaKG.answerComment.service.AnswerCommentService;
import com.instaKG.board.domain.Board;
import com.instaKG.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerCommentController {
    private final BoardService boardService;
    private final AnswerService answerService;
    private final AnswerCommentService answerCommentService;

    @PostMapping("/create/detail/{boardId}/{answerId}")
    public String createAnswer(Model model,@PathVariable("boardId") Long boardId, @PathVariable("answerId") Long answerId, @Valid AnswerCommentForm answerCommentForm, BindingResult bindingResult){
        Answer answer = this.answerService.getAnswer(answerId);
        Board board = this.boardService.getBoard(boardId);
        if(bindingResult.hasErrors()){
            model.addAttribute("answer", answer);
            return "board_detail";
        }
        this.answerCommentService.create(answer, board, answerCommentForm.getContent());
        return String.format("redirect:/board/list/detail/%s", boardId);
    }

    @PostMapping("/comment/detail/like/{id}")
    @ResponseBody
    public Integer createCommentsAnswer(@PathVariable("id") Long id) {
        return this.answerCommentService.setLike(id);
    }
}