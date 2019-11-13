package com.softsquared.softsquared_daum_cafe.src;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.softsquared.softsquared_daum_cafe.config.XAccessTokenInterceptor;
import com.softsquared.softsquared_daum_cafe.src.signselect.SignSelectActivity;
import com.softsquared.softsquared_daum_cafe.src.splash.models.UserInfoResponse;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {

    private static volatile ApplicationClass instance = null;

    public static ApplicationClass getApplicationClassContext() {
        if (instance == null)
            throw new IllegalStateException("Error of ApplicationClass");
        return instance;
    }

    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=uft-8");
    public static MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

    // 테스트 서버 주소
    public static String BASE_URL = "http://ec2-13-209-57-34.ap-northeast-2.compute.amazonaws.com:3000/";
    // 실서버 주소
//    public static String BASE_URL = "https://template.softsquared.com/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "DAUM_APP";

    // JWT Token 값
    public static String X_ACCESS_TOKEN = "X-ACCESS-TOKEN";

    // User Login 값
    public static boolean isUserLogin = false;
    public static String userId;
    public static String userName;
    public static String userProfileImg;

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Kakao Login 인스턴스
    private static class KakaoSDKAdapter extends KakaoAdapter {

        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_TALK};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return ApplicationClass.getApplicationClassContext();
                }
            };
        }
    }

    // Retrofit 인스턴스
    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(new XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
