package com.softsquared.softsquared_daum_cafe.src.signselect.signup.interfaces;

import com.softsquared.softsquared_daum_cafe.src.signselect.signup.models.SignUpRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signup.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {

    @POST("/app/signUp")
    Call<SignUpResponse> getResponse(@Body SignUpRequest userInfo);
}
