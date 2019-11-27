package com.softsquared.softsquared_daum_cafe.src.articledetail.models;

public class Comment {
    private int id;
    private String contents;
    private String userId;
    private String createAt;
    private String imgUri;

    public Comment(String contents, String userId, String createAt, String imgUri) {
        this.contents = contents;
        this.userId = userId;
        this.createAt = createAt;
        this.imgUri = imgUri;
    }

    public int getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getImgUri() {
        return imgUri;
    }
}
