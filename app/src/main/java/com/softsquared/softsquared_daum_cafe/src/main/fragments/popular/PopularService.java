package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.interfaces.PopularFragmentView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.interfaces.PopularRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class PopularService {
    final PopularFragmentView mPopularFragmentView;

    public PopularService(PopularFragmentView mPopularFragmentView) {
        this.mPopularFragmentView = mPopularFragmentView;
    }

    public void getPopularArticles() {
        final PopularRetrofitInterface popularRetrofitInterface = getRetrofit().create(PopularRetrofitInterface.class);
        popularRetrofitInterface.getPopular().enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                final PopularResponse popularResponse = response.body();
                if (popularResponse == null || !popularResponse.getIsSuccess()) {
                    mPopularFragmentView.validateFailure(null);
                    return;
                }
                mPopularFragmentView.validateSuccess(popularResponse.getResults());
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                t.printStackTrace();
                mPopularFragmentView.validateFailure(null);
            }
        });
    }
}
