package hello.hellospring.controller;

import hello.hellospring.domain.BoardDto;
import hello.hellospring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/save")
    public String writeForm(){
        return "board/save";
    }

    @PostMapping("/board/save")
    public String write(@ModelAttribute BoardDto boardDto){
        System.out.println("boardDto = " + boardDto);
        boardService.save(boardDto);
        return "home";
    }
}
