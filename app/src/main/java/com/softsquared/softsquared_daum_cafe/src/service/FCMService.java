package com.softsquared.softsquared_daum_cafe.src.service;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.softsquared.softsquared_daum_cafe.src.splash.SplashService;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.FCM_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        //  최초 한번만 발급. 저장했다가.
        sSharedPreferences.edit().putString(FCM_TOKEN, s).apply();
        // 서버로 FCM 토큰 보내야 함.
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // 받은 메세지 분석
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);
    }
}
