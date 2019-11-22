package com.softsquared.softsquared_daum_cafe.src.cafe.models;

import java.util.ArrayList;

public class Category {
    String title;
    String category;
    boolean userStarred;

    public Category(String title, String category, boolean userStarred) {
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
