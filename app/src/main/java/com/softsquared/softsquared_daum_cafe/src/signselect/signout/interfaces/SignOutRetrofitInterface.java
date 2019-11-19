package com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces;

import com.softsquared.softsquared_daum_cafe.src.signselect.signout.models.SignOutRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.models.SignOutResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;

public interface SignOutRetrofitInterface {
    @DELETE("user/resign")
    Call<SignOutResponse> resignUser();
}
