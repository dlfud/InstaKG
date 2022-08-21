package com.instaKG.board.controller;

import com.instaKG.board.BoardForm;
import com.instaKG.board.domain.Board;
import com.instaKG.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @RequestMapping("/list")
    public String board(Model model){
        List<Board> boardList = this.boardService.getList();
        Collections.sort(boardList, (a, b) -> (int) (b.getId() - a.getId()));
        model.addAttribute("boardList", boardList);
        return "/board";
    }


    @RequestMapping("/list/detail/{id}")
    public String boardDetail(Model model, @PathVariable("id") Long id){
        Board board = this.boardService.getBoard(id);
        Collections.sort(board.getAnswerList(), (a, b) -> (int) (b.getId() - a.getId()));
        model.addAttribute("board", board);
        return "board_detail";
    }

    @GetMapping("/create")
    public String boardCreate(BoardForm boardForm){
        return "board_form";
    }


    @PostMapping("/like/{id}")
    @ResponseBody
    public String boardLike(@PathVariable("id") Long id){
        this.boardService.setLike(id);
        return "redirect:/board/list";
    }

    @PostMapping("/detail/like/{id}")
    public String boardDetailLike(@PathVariable("id") Long id){
        this.boardService.setLike(id);
        return String.format("redirect:/board/list/detail/%s", id);
    }
}
