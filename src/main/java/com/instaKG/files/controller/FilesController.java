package com.instaKG.files.controller;

import com.instaKG.board.BoardForm;
import com.instaKG.board.service.BoardService;
import com.instaKG.files.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilesController {
    private final FilesService filesService;
    private final BoardService boardService;

    @GetMapping("/multi-file")
    public String showMultiForm(){
        return "board_form";
    }

    @PostMapping("/multi-file")
    public String multiFileUpload(
            Model model,
            @RequestParam("multiFile") List<MultipartFile> multiFileList,
            BoardForm boardForm
    )throws InterruptedException{
        try{
            filesService.upload(boardForm, multiFileList);
        }catch(Exception e){
            boardService.create(boardForm.getContent());
        }
        return "redirect:/board/list";
    }
}
