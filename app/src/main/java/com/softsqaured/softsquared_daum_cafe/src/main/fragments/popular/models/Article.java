package com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.models;

public class Article {

    private String articleTitle;
    private String cafeName;
    private String urlImg;

    public Article(String articleTitle, String cafeName, String urlImg) {
        this.articleTitle = articleTitle;
        this.cafeName = cafeName;
        this.urlImg = urlImg;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
