package hello.hellospring.domain;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class BoardDto {
        private long id;
        private String boardWriter;
        private String boardPass;
        private String boardTitle;
        private String boardContent;
        private int boardHits;
        private LocalDateTime boardCreatedTime;

    public BoardDto() {
    }

    public BoardDto(long id, String boardWriter, String boardPass, String boardTitle, String boardContent, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardPass = boardPass;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
