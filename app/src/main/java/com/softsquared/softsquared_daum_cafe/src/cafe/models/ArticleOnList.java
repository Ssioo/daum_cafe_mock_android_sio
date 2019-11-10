package com.softsquared.softsquared_daum_cafe.src.cafe.models;

public class ArticleOnList {
    private String title;
    private String author;
    private String createDate;
    private String viewCount;
    private String commentCount;
    private String board;

    public ArticleOnList() {
    }

    public ArticleOnList(String title, String author, String createDate, String viewCount, String commentCount, String board) {
        this.title = title;
        this.author = author;
        this.createDate = createDate;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.board = board;
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

    public String getViewCount() {
        return viewCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getBoard() {
        return board;
    }
}
