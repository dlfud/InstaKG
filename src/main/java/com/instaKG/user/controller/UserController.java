package com.instaKG.user.controller;

import com.instaKG.board.domain.Board;
import com.instaKG.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final BoardService boardService;


    @RequestMapping("/profile")
    public String profile(Model model){
        List<Board> boardList = this.boardService.getList();
        Collections.sort(boardList, (a, b) -> (int) (b.getId() - a.getId()));
        model.addAttribute("board", boardList);
        return "/profile";
    }

    @GetMapping("/setprofile")
//    @ResponseBody
    public String setprofile(){
        return "/setprofile";
    }
}
