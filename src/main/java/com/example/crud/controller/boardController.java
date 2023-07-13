package com.example.crud.controller;

import com.example.crud.Dto.BoardDto;
import com.example.crud.model.Board;
import com.example.crud.repository.UserRepository;
import com.example.crud.vailidator.BoardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class boardController {

    @Autowired
    private com.example.crud.repository.BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private UserRepository userRepository;


    @GetMapping({"/board", "/board/list"})
    public String board(Model model,
                        @PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String keyword
    ) {

        List<BoardDto> boardDtoList =  new ArrayList<>();
        Page<Board> boards = boardRepository.findAllByTitleContainingOrContentContaining(keyword, keyword, pageable);

        // boards to boardDtoList
        boards.getContent().forEach(board -> {
            BoardDto boardDto = new BoardDto();
            boardDto.setId(board.getId());
            boardDto.setTitle(board.getTitle());
            boardDto.setContent(board.getContent());
            boardDto.setNickname(board.getAuthor().getNickname());
            boardDto.setCreated_at(board.getCreated_at());
            boardDto.setUpdated_at(board.getUpdated_at());
            boardDtoList.add(boardDto);
        });

        int startpage = Math.max(0, boards.getPageable().getPageNumber() - 1);
        int endtpage = Math.min(boards.getTotalPages(), boards.getTotalPages() + 4);
        model.addAttribute("startPage", startpage);
        model.addAttribute("endPage", endtpage);
        model.addAttribute("board_p", boards);
        model.addAttribute("boards", boardDtoList);

        return "board/list";
    }

    @GetMapping({"/form"})
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null){
            model.addAttribute("board", new Board());
        }else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping({"/form"})
    public String postForm(@Valid Board board, BindingResult bindingResult){
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()){
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @PostMapping({"/board/delete"})
    public String delete(@RequestParam Long id) {
        boardRepository.deleteById(id);
        return "redirect:/board/list";
    }

}

