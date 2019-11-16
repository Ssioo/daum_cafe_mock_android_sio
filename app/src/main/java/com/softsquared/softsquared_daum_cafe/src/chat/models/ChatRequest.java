package com.softsquared.softsquared_daum_cafe.src.chat.models;

import com.google.gson.annotations.SerializedName;

public class ChatRequest {
    @SerializedName("userId") String userName;
    @SerializedName("content") String content;
    @SerializedName("time") long time;

    public ChatRequest() {
    }

    public ChatRequest(String userName, String content, long time) {
        this.userName = userName;
        this.content = content;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public long getTime() {
        return time;
    }
}
