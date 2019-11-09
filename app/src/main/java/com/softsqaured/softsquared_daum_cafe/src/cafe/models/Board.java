package com.softsqaured.softsquared_daum_cafe.src.cafe.models;

public class Board {
    String title;
    String category;
    boolean userStarred;

    public Board(String title, String category, boolean userStarred) {
        this.title = title;
        this.category = category;
        this.userStarred = userStarred;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isUserStarred() {
        return userStarred;
    }

    public void setUserStarred(boolean userStarred) {
        this.userStarred = userStarred;
    }
}
