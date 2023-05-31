package hello.hellospring.service;

import hello.hellospring.domain.BoardDto;
import hello.hellospring.domain.BoardEntity;
import hello.hellospring.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        boardEntity.setId(createBoardId());
        boardRepository.save(boardEntity);
    }

    public List<BoardEntity> findBoards(){
        return boardRepository.findAll();
    }

    public Optional<BoardEntity> findBoardById(Long BoardId){
        return boardRepository.findById(BoardId);
    }

    public Long createBoardId(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        Long id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        while(true){
            if(findBoardById(id).isEmpty()){
                return id;
            }else{
                id = random.nextLong();
            }
        }
    }
}
