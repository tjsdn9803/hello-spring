package hello.hellospring.controller;

import hello.hellospring.domain.BoardDto;
import hello.hellospring.domain.BoardEntity;
import hello.hellospring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        boardService.save(boardDto);
        return "home";
    }

    @GetMapping("/board/list")
    public String BoardList(Model model){
        List<BoardEntity> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "board/boardList";

    }

    @GetMapping("/board/view")
    public String BoardView(Model model, Long id){
        BoardEntity boardEntity = boardService.findBoardById(id).get();
        System.out.println(boardEntity.getBoardContent());
        model.addAttribute("board", boardEntity);
        return "board/boardView";
    }
}
