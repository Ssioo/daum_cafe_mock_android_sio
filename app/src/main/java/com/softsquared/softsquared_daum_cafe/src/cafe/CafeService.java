package com.softsquared.softsquared_daum_cafe.src.cafe;

import com.softsquared.softsquared_daum_cafe.src.cafe.interfaces.CafeActivityView;
import com.softsquared.softsquared_daum_cafe.src.cafe.interfaces.CafeRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class CafeService {
    final CafeActivityView mCafeActivityView;

    public CafeService(CafeActivityView mCafeActivityView) {
        this.mCafeActivityView = mCafeActivityView;
    }

    public void getArticles(String cafeName) {
        final CafeRetrofitInterface cafeRetrofitInterface = getRetrofit().create(CafeRetrofitInterface.class);
        cafeRetrofitInterface.getArticles(cafeName).enqueue(new Callback<CafeResponse>() {
            @Override
            public void onResponse(Call<CafeResponse> call, Response<CafeResponse> response) {
                final CafeResponse cafeResponse = response.body();
                if (cafeResponse == null || !cafeResponse.getIsSuccess()) {
                    mCafeActivityView.validateFailure(null);
                    return;
                }
                mCafeActivityView.validateSuccess(cafeResponse.getResults());
            }

            @Override
            public void onFailure(Call<CafeResponse> call, Throwable t) {
                t.printStackTrace();
                mCafeActivityView.validateFailure(null);
            }
        });
    }
}
