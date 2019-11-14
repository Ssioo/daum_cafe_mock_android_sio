package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.interfaces;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.models.AddCafeRequest;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.models.AddCafeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddCafeRetrofitInterface {
    @POST("cafe/create")
    Call<AddCafeResponse> postCafe(@Body AddCafeRequest addCafeRequest);
}
