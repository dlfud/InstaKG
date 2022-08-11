package com.instaKG.board.controller;

import com.instaKG.board.BoardForm;
import com.instaKG.board.domain.Board;
import com.instaKG.board.service.BoardService;
import com.instaKG.files.service.FilesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;
    private FilesService filesService;

    @GetMapping("/create")
    public String boardCreate(BoardForm boardForm){
        return "/board_form";
    }

    @PostMapping("/create")
    public String multiFileUpload(
            Model model,
            @RequestParam("multiFile") List<MultipartFile> multiFileList,
            BoardForm boardForm
    )throws InterruptedException{
        try{
            filesService.upload(boardForm, multiFileList);
        }catch(Exception e){
            boardService.create(boardForm.getTitle(), boardForm.getContent());
        }
        return "redirect:/board/post";
    }



    @RequestMapping("/post")
    public String post(Model model){
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
        return "/post";
    }

    @RequestMapping("/post/detail/{id}")
    public String postDetail(Model model, @PathVariable("id") Long id){
        Board board = this.boardService.getBoard(id);
        model.addAttribute("board", board);
        return "/post_detail";
    }
}
