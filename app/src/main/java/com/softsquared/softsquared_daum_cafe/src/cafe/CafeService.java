package com.softsquared.softsquared_daum_cafe.src.cafe;

import com.softsquared.softsquared_daum_cafe.src.cafe.interfaces.CafeActivityView;
import com.softsquared.softsquared_daum_cafe.src.cafe.interfaces.CafeRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CafeResponse;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.CategoryResponse;

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

    public void getCategories() {
        final CafeRetrofitInterface cafeRetrofitInterface = getRetrofit().create(CafeRetrofitInterface.class);
        cafeRetrofitInterface.getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                final CategoryResponse categoryResponse = response.body();
                if (categoryResponse == null || !categoryResponse.getIsSuccess()) {
                    mCafeActivityView.validateFailure(categoryResponse == null ? null : categoryResponse.getMessage());
                    return;
                }
                mCafeActivityView.validateCategorySuccess(categoryResponse.getResults());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                t.printStackTrace();
                mCafeActivityView.validateFailure(null);
            }
        });
    }
}
