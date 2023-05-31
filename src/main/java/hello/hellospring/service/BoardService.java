package hello.hellospring.service;

import hello.hellospring.domain.BoardDto;
import hello.hellospring.domain.BoardEntity;
import hello.hellospring.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);
    }

    public List<BoardEntity> findBoards(){
        return boardRepository.findAll();
    }
}
