package com.softsqaured.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model;

public class CafeListItem {
    String cafeThumbnail;
    String cafeTitle;
    String cafeDate;
    boolean favorited;

    public CafeListItem() {
    }

    public CafeListItem(String cafeThumbnail, String cafeTitle, String cafeDate, boolean favorited) {
        this.cafeThumbnail = cafeThumbnail;
        this.cafeTitle = cafeTitle;
        this.cafeDate = cafeDate;
        this.favorited = favorited;
    }

    public String getCafeThumbnail() {
        return cafeThumbnail;
    }

    public void setCafeThumbnail(String cafeThumbnail) {
        this.cafeThumbnail = cafeThumbnail;
    }

    public String getCafeTitle() {
        return cafeTitle;
    }

    public void setCafeTitle(String cafeTitle) {
        this.cafeTitle = cafeTitle;
    }

    public String getCafeDate() {
        return cafeDate;
    }

    public void setCafeDate(String cafeDate) {
        this.cafeDate = cafeDate;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
