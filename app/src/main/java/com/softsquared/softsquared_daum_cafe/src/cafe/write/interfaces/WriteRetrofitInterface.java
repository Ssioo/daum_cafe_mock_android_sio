package com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces;

import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteRequest;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WriteRetrofitInterface {
    @POST("board/post")
    Call<WriteResponse> postArticle(@Body WriteRequest writeRequest);

    @PATCH("board/{boardid}/modifyPost")
    Call<WriteResponse> patchArticle(@Path ("boardid") String boardId, @Body WriteRequest writeRequest);

    @DELETE("board/{boardid}")
    Call<WriteResponse> deleteArticle(@Path("boardid") String boardid);
}
