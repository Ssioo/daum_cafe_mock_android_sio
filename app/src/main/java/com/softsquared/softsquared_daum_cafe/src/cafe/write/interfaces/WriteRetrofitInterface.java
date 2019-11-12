package com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces;

import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteRequest;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WriteRetrofitInterface {
    @POST("board/post")
    Call<WriteResponse> postArticle(@Body WriteRequest writeRequest);
}
