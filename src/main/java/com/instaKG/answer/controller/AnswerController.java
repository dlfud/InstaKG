package com.instaKG.answer.controller;

import com.instaKG.answer.AnswerForm;
import com.instaKG.answer.domain.Answer;
import com.instaKG.answer.service.AnswerService;
import com.instaKG.board.domain.Board;
import com.instaKG.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final BoardService boardService;
    private final AnswerService answerService;

    @RequestMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult){
        Board board = this.boardService.getBoard(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("board", board);
            return "board";
        }
        this.answerService.create(board, answerForm.getContent());
        return "redirect:/board/list";
    }

    @RequestMapping("/create/detail/{id}")
    public String createDetailAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult){
        Board board = this.boardService.getBoard(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("board", board);
            return "board_detail";
        }
        this.answerService.create(board, answerForm.getContent());
        return String.format("redirect:/board/detail/%s", id);
    }

    @RequestMapping("/delete/{id}")
    public String deleteAnswer(Model model, @PathVariable("id") Long id){
        Answer answer = this.answerService.getAnswer(id);
        this.answerService.delete(answer);
        return String.format("redirect:/board/detail/%s", answer.getBoard().getId());
    }

    @PostMapping("/list/like/{id}")
    public String answerLike(@PathVariable("id") Long id){
        this.answerService.setReplyLike(id);
        return "redirect:/board/list";
    }
}
