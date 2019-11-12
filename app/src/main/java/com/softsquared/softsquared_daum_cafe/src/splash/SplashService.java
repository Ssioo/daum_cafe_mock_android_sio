package com.softsquared.softsquared_daum_cafe.src.splash;

import android.util.Log;

import com.softsquared.softsquared_daum_cafe.src.splash.interfaces.SplashRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.splash.interfaces.SplashActivityView;
import com.softsquared.softsquared_daum_cafe.src.splash.models.AutoSignInResponse;
import com.softsquared.softsquared_daum_cafe.src.splash.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

public class SplashService {

    final SplashActivityView mSplashActivityView;

    public SplashService(SplashActivityView mSplashActivityView) {
        this.mSplashActivityView = mSplashActivityView;
    }

    void getAutoLogin() {
        // 기존 JWT 토큰으로 로그인
        Log.i("AutoLogin", "processing...");
        final SplashRetrofitInterface splashRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);
        splashRetrofitInterface.postAutoSignIn().enqueue(new Callback<AutoSignInResponse>() {
            @Override
            public void onResponse(Call<AutoSignInResponse> call, Response<AutoSignInResponse> response) {
                final AutoSignInResponse autoSignInResponse = response.body();
                if (autoSignInResponse == null || !autoSignInResponse.getIsSuccess()) {
                    mSplashActivityView.validateFailure(null);
                    Log.i("AutoLogin", String.valueOf(autoSignInResponse.getCode()));
                    return;
                }
                Log.i("AutoSignInReceive", "processing...");
                // JWT 토큰으로 로그인 후 정보 조회
                splashRetrofitInterface.getUserInfo().enqueue(new Callback<UserInfoResponse>() {
                    @Override
                    public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                        final UserInfoResponse userInfo = response.body();
                        if (userInfo == null || !userInfo.getIsSuccess()) {
                            mSplashActivityView.validateFailure(null);
                            return;
                        }
                        // userinfo 받아옴
                        Log.i("UserInfoReceived", "ok");
                        mSplashActivityView.validateSuccess(userInfo.getUserInfo().get(0).getUserEmail(), userInfo.getUserInfo().get(0).getUserName());
                    }

                    @Override
                    public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                        Log.i("UserInfoReceived", "fail");
                        t.printStackTrace();
                        mSplashActivityView.validateFailure(null);
                    }
                });
            }

            @Override
            public void onFailure(Call<AutoSignInResponse> call, Throwable t) {
                Log.i("AutoLogin", "processing...Failed");
                t.printStackTrace();
                mSplashActivityView.validateFailure(null);
            }
        });
    }

}
