package com.example.crud.controller;

import com.example.crud.Dto.BoardDto;
import com.example.crud.model.Board;
import com.example.crud.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {


    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/board/get")
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @PostMapping("/board/post")
    public Board boardPost(@RequestParam Board newBoard) {
        return boardRepository.save(newBoard);
    }

    @GetMapping("/board/get/{id}")
    Board getBoard(@PathVariable Long id){
        return boardRepository.findById(id).orElse(null);
    }

    @PostMapping("/board/post/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id){
        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return boardRepository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardRepository.save(newBoard);
                });
    }

    @GetMapping("/test/board/get")
    public List<BoardDto> testBoardList() {
        List<BoardDto> boardDtoList =  new ArrayList<>();
        List<Board> boards = boardRepository.findAll();

        boards.forEach(board -> {
            BoardDto boardDto = new BoardDto();
            boardDto.setId(board.getId());
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            boardDto.setNickname(board.getAuthor().getNickname());
            boardDto.setCreated_at(board.getCreated_at());
            boardDto.setUpdated_at(board.getUpdated_at());
            boardDtoList.add(boardDto);
        });

        return boardDtoList;
    }

}
