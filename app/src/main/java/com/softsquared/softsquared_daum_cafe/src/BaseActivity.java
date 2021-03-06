package com.softsquared.softsquared_daum_cafe.src;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.signselect.SignSelectActivity;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.BASE_URL_FIREBASE_STORAGE;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.FCM_TOKEN;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    public ProgressDialog mProgressDialog;
    public AlertDialog mLoginAlert;

    public static FirebaseStorage firebaseStorage;
    public static StorageReference imageStorageRef;
    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference chatDatabase;


    public static int dpUnit;

    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    public void startNextActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void startNextActivityForResult(Class<?> activity, int requestCode) {
        Intent intent = new Intent(this, activity);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivityForResultWithData(Class<?> activity, int requestCode, String tag, String data) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(tag, data);
        startActivityForResult(intent, requestCode);
    }

    public void startNextActivity(Class<?> activity, final int flag) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(flag);
        startActivity(intent);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void showUploadProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.uploading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // Constants - DP to PX
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        dpUnit = (int) metrics.density;

        Log.i("FCM TOKEN", sSharedPreferences.getString(FCM_TOKEN, ""));

        // Login Alert
        /* AlertDialog Init */
        mLoginAlert = new AlertDialog.Builder(this).setMessage(getString(R.string.alert_login))
                .setPositiveButton(getString(R.string.alert_positive_login), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startNextActivity(SignSelectActivity.class);
                        dialog.dismiss();
                    }
                }).setNegativeButton(getString(R.string.alert_negative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setCancelable(true).create();

        // FirebaseStorage 인스턴스
        firebaseStorage = FirebaseStorage.getInstance(BASE_URL_FIREBASE_STORAGE);
        imageStorageRef = firebaseStorage.getReference().child("images"); // images 폴더 참조.
        // FIrebaseMessaging 인스턴스 얻기
        if (sSharedPreferences.getString(FCM_TOKEN, null) == null) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "getInstanceId failed", task.getException());
                                return;
                            }
                            Log.i(FCM_TOKEN, task.getResult().getToken());
                            sSharedPreferences.edit().putString(FCM_TOKEN, task.getResult().getToken()).apply();

                        }
                    });
        }
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        // Firebase RealtimeDatabase 인스턴스
        firebaseDatabase = FirebaseDatabase.getInstance();
        chatDatabase = firebaseDatabase.getReference("chat");
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
        hideProgressDialog();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
