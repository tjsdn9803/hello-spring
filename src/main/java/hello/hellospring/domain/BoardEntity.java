package hello.hellospring.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContent;

    @Column
    private int boardHits;

    @Column
    private LocalDateTime boardCreatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public String getBoardPass() {
        return boardPass;
    }

    public void setBoardPass(String boardPass) {
        this.boardPass = boardPass;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public int getBoardHits() {
        return boardHits;
    }

    public void setBoardHits(int boardHits) {
        this.boardHits = boardHits;
    }

    public LocalDateTime getBoardCreatedTime() {
        return boardCreatedTime;
    }

    public void setBoardCreatedTime(LocalDateTime boardCreatedTime) {
        this.boardCreatedTime = boardCreatedTime;
    }

    public static BoardEntity toSaveEntity(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardContent(boardDto.getBoardContent());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
