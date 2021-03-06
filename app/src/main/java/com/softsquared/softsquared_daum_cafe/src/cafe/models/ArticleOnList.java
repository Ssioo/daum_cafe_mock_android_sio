package com.softsquared.softsquared_daum_cafe.src.cafe.models;

public class ArticleOnList {
    private int boardId;
    private String title;
    private String author;
    private String createDate;
    private int viewCount;
    private int commentCount;
    private String board;

    public ArticleOnList() {
    }

    public ArticleOnList(int boardId, String title, String author, String createDate, int viewCount, int commentCount, String board) {
        this.boardId = boardId;
        this.title = title;
        this.author = author;
        this.createDate = createDate;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.board = board;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getBoard() {
        return board;
    }


}
