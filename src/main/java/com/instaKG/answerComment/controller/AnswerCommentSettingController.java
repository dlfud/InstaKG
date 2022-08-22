package com.instaKG.answerComment.controller;

import com.instaKG.answer.AnswerForm;
import com.instaKG.answerComment.AnswerCommentForm;
import com.instaKG.answerComment.domain.AnswerComment;
import com.instaKG.answerComment.service.AnswerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/answerComment")
@RequiredArgsConstructor
public class AnswerCommentSettingController {

    private final AnswerCommentService answerCommentService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        this.answerCommentService.delete(answerComment);
        return String.format("redirect:/question/list/detail/%s",answerComment.getBoard().getId());
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        answerForm.setContent(answerForm.getContent());
        model.addAttribute("answer", answerComment);
        return "AnswerCommentModify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Long id, @RequestParam(value = "onOff", required = false) Boolean onOff, @Valid AnswerCommentForm answerCommentForm, BindingResult bindingResult) {
        AnswerComment answerComment = this.answerCommentService.getAnswerComment(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("answer", answerComment);
            return "AnswerCommentModify";
        }
        this.answerCommentService.modify(answerComment, answerCommentForm.getContent());
        return String.format("redirect:/question/list/detail/%s",answerComment.getBoard().getId());
    }

}
