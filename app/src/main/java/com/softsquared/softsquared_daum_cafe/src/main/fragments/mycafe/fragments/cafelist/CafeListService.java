package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces.CafeListFragmentView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.interfaces.CafeListRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class CafeListService {
    final CafeListFragmentView mCafeListFragmentView;

    public CafeListService(CafeListFragmentView cafeListFragmentView) {
        this.mCafeListFragmentView = cafeListFragmentView;
    }

    public void getCafeList() {
        final CafeListRetrofitInterface cafeListRetrofitInterface = getRetrofit().create(CafeListRetrofitInterface.class);
        cafeListRetrofitInterface.getCafeList().enqueue(new Callback<CafeListResponse>() {
            @Override
            public void onResponse(Call<CafeListResponse> call, Response<CafeListResponse> response) {
                final CafeListResponse cafeListResponse = response.body();
                if (cafeListResponse == null || !cafeListResponse.getIsSuccess()) {
                    mCafeListFragmentView.validateFailure((cafeListResponse == null) ? null : cafeListResponse.getMessage());
                    return;
                }
                if (cafeListResponse.getResults() == null) {
                    mCafeListFragmentView.validateFailure(cafeListResponse.getMessage());
                    return;
                }
                mCafeListFragmentView.validateSuccess(cafeListResponse.getResults());
            }

            @Override
            public void onFailure(Call<CafeListResponse> call, Throwable t) {
                t.printStackTrace();
                mCafeListFragmentView.validateFailure(null);
            }
        });
    }
}
