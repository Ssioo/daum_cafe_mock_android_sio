package com.softsquared.softsquared_daum_cafe.src.cafe.write;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces.WriteActivityView;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces.WriteRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteRequest;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteResponse;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;
import static com.softsquared.softsquared_daum_cafe.src.BaseActivity.imageStorageRef;

public class WriteService {
    final WriteActivityView mWriteActivityView;

    public WriteService(WriteActivityView mWriteActivityView) {
        this.mWriteActivityView = mWriteActivityView;
    }

    public void postArticle(String title, String contents, String categoryType, String cafeName, String imgUri) {
        final WriteRetrofitInterface writeRetrofitInterface = getRetrofit().create(WriteRetrofitInterface.class);
        writeRetrofitInterface.postArticle(new WriteRequest(title, contents, categoryType, cafeName, imgUri)).enqueue(new Callback<WriteResponse>() {
            @Override
            public void onResponse(Call<WriteResponse> call, Response<WriteResponse> response) {
                final WriteResponse writeResponse = response.body();
                if (writeResponse == null || !writeResponse.getIsSuccess()) {
                    mWriteActivityView.validateUploadFailure((writeResponse == null) ? null : writeResponse.getCode() + ": " + writeResponse.getMessage());
                    return;
                }

                Log.i("WriteAfterFirebase", "글 업로드 성공");
                mWriteActivityView.validateUploadSuccess(writeResponse.getMessage());
            }

            @Override
            public void onFailure(Call<WriteResponse> call, Throwable t) {
                Log.i("WriteAfterFirebase", "글 업로드 실패");
                mWriteActivityView.validateUploadFailure(null);
                t.printStackTrace();
            }
        });
    }

    public void postImgToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        final Date date = new Date();
        UploadTask uploadTask = imageStorageRef.child(date.getTime() + ".jpg").putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("FirebaseStorage", "이미지 업로드 실패");
                mWriteActivityView.validateUploadImageFailure(null);
                e.printStackTrace();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("FirebaseStorage", "이미지 업로드 완료");
                mWriteActivityView.validateUploadImageSuccess(date.getTime() + ".jpg");
            }
        });
    }
}
