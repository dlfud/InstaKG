package com.instaKG.answer.controller;

import com.instaKG.answer.AnswerForm;
import com.instaKG.answer.domain.Answer;
import com.instaKG.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentSettingController {

    private final AnswerService answerService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Answer answer = this.answerService.getAnswer(id);
        this.answerService.delete(answer);
        return String.format("redirect:/board/list/detail/%s",answer.getBoard().getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
        Answer answer = this.answerService.getAnswer(id);
        answerForm.setContent(answerForm.getContent());
        model.addAttribute("answer", answer);
        return "CommentModify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Long id, @RequestParam(value = "onOff", required = false) Boolean onOff, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Answer answer = this.answerService.getAnswer(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("answer", answer);
            return "CommentModify";
        }
        this.answerService.modify(answer, answerForm.getContent(), onOff);
        return String.format("redirect:/board/list/detail/%s",answer.getBoard().getId());
    }

}