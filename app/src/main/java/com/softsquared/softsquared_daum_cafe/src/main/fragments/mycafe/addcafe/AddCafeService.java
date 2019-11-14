package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe;

import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.interfaces.AddCafeActivityView;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.interfaces.AddCafeRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.models.AddCafeRequest;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.addcafe.models.AddCafeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class AddCafeService {
    final AddCafeActivityView mAddCafeActivityView;

    public AddCafeService(AddCafeActivityView mAddCafeActivityView) {
        this.mAddCafeActivityView = mAddCafeActivityView;
    }

    public void postCafe(String cafeName) {
        final AddCafeRetrofitInterface addCafeRetrofitInterface = getRetrofit().create(AddCafeRetrofitInterface.class);
        addCafeRetrofitInterface.postCafe(new AddCafeRequest(cafeName)).enqueue(new Callback<AddCafeResponse>() {
            @Override
            public void onResponse(Call<AddCafeResponse> call, Response<AddCafeResponse> response) {
                final AddCafeResponse addCafeResponse = response.body();
                if (addCafeResponse == null || !addCafeResponse.getIsSuccess()) {
                    mAddCafeActivityView.validateFailure(null);
                    return;
                }
                mAddCafeActivityView.validateSuccess(null);
            }

            @Override
            public void onFailure(Call<AddCafeResponse> call, Throwable t) {
                t.printStackTrace();
                mAddCafeActivityView.validateFailure(null);
            }
        });
    }
}
