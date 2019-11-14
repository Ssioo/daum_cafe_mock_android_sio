package com.softsquared.softsquared_daum_cafe.src.cafe.write;

import com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces.WriteActivityView;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces.WriteRetrofitInterface;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteRequest;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.models.WriteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.getRetrofit;

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
                    mWriteActivityView.validateFailure(null);
                    return;
                }

                mWriteActivityView.validateSuccess(writeResponse.getMessage());
            }

            @Override
            public void onFailure(Call<WriteResponse> call, Throwable t) {
                mWriteActivityView.validateFailure(null);
                t.printStackTrace();
            }
        });
    }
}
