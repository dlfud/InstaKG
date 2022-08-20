package com.instaKG.board.service;

import com.instaKG.board.dao.BoardRepository;
import com.instaKG.board.domain.Board;
import com.instaKG.files.domain.Files;
import com.instaKG.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList(){
        return this.boardRepository.findAll();
    }

    public Board getBoard(Long id){
        Optional<Board> board = this.boardRepository.findById(id);
        if(board.isPresent()){
            return board.get();
        }else{
            throw new DataNotFoundException("board not found");
        }
    }

    public Board create(String content){
        Board board = new Board();
        board.setContent(content);
        board.setCreateDate(LocalDateTime.now());
        board.setModifyDate(LocalDateTime.now());
        board.setReplyLike(0);
        this.boardRepository.save(board);
        return board;
    }

    public void setLike(Long boardId){
        Board board = boardRepository.findById(boardId).get();

        if(board.getReplyLike() == 0){
            board.setReplyLike(1);
        }else{
            board.setReplyLike(0);
        }
        this.boardRepository.save(board);
    }

    public void delete(Board board){
        String root = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadFiles";
        for(Files file: board.getFileList()){
            new File(root + "\\" + file.getFileName()).delete();
        }
        this.boardRepository.delete(board);
    }

    public void modify(Board board, String content, Boolean onOff){
        board.setContent(content);
        board.setModifyDate(LocalDateTime.now());
        board.setOnOff(onOff);
        this.boardRepository.save(board);
    }

}
