package com.softsquared.softsquared_daum_cafe.src.signselect.signout;

import android.util.Log;

import com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces.SignOutActivityView;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.interfaces.SignOutRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.models.SignOutRequest;
import com.softsquared.softsquared_daum_cafe.src.signselect.signout.models.SignOutResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class SignOutService {
    final SignOutActivityView mSignOutActivityView;

    public SignOutService(SignOutActivityView mSignOutActivityView) {
        this.mSignOutActivityView = mSignOutActivityView;
    }

    public void tryResign() {
        final SignOutRetrofitInterface signOutRetrofitInterface = getRetrofit().create(SignOutRetrofitInterface.class);
        signOutRetrofitInterface.resignUser().enqueue(new Callback<SignOutResponse>() {
            @Override
            public void onResponse(Call<SignOutResponse> call, Response<SignOutResponse> response) {
                SignOutResponse signOutResponse = response.body();
                Log.i("SIGNOUT", "SUCCESS");
                if (signOutResponse == null || !signOutResponse.getIsSuccess()) {
                    Log.i("SIGNOUT", "FAILURE");
                    mSignOutActivityView.validateFailure((signOutResponse == null) ? null : signOutResponse.getMessage());
                    return;
                }
                mSignOutActivityView.validateSuccess(null);
            }

            @Override
            public void onFailure(Call<SignOutResponse> call, Throwable t) {
                t.printStackTrace();
                mSignOutActivityView.validateFailure(null);
            }
        });
    }
}
