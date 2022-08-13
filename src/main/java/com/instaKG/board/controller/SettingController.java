package com.instaKG.board.controller;

import com.instaKG.board.BoardForm;
import com.instaKG.board.domain.Board;
import com.instaKG.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController {
    private final BoardService boardService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Board board = this.boardService.getBoard(id);
        this.boardService.delete(board);
        return "redirect:/board/post";
    }


    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Long id, BoardForm boardForm){
        Board board = this.boardService.getBoard(id);
        boardForm.setContent(boardForm.getContent());
        model.addAttribute("board", board);
        return "/modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Long id, @RequestParam(value="onOff", required = false) Boolean onOff, @Valid BoardForm boardForm, BindingResult bindingResult){
        Board board = this.boardService.getBoard(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("board", board);
            return "/modify";
        }
        this.boardService.modify(board, boardForm.getContent(), onOff);
        return "redirect:/board/post";
    }

}
