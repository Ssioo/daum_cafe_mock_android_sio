package com.softsquared.softsquared_daum_cafe.src.cafe.write;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.cafe.write.interfaces.WriteActivityView;

import java.io.IOException;
import java.io.InputStream;

public class WriteActivity extends BaseActivity implements WriteActivityView {

    private static final int REQUEST_FROM_ALBUM = 1;
    private static final int MAXIMUM_IMG_WIDTH = 1080;
    private static final int MAXIMUM_IMG_HEIGHT = 2280;

    private static final int MODE_CREATE = 10;
    private static final int MODE_EDIT = 11;


    private int activityMode = 0;

    private Toolbar tbWrite;
    private Button btnSubmit;
    private Button btnSave;
    private ImageView ivCamera;
    private ImageView ivMedia;
    private ImageView ivClip;
    private ImageView ivLocation;
    private ImageView ivLink;
    private ImageView ivEmoji;
    private ImageView ivStorage;
    private ImageView ivSetting;
    private ImageView ivImg;
    private Bitmap bmIvImg;
    private EditText etTitle;
    private EditText etContents;

    private boolean IMAGE_ATTACHED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        /* get Intent */
        Intent intent = getIntent();
        String modeStr = intent.getStringExtra("activityMode");
        if (modeStr != null && modeStr.equals("CREATE"))
            activityMode = MODE_CREATE;
        else if (modeStr != null && modeStr.equals("EDIT"))
            activityMode = MODE_EDIT;

        if (activityMode == MODE_EDIT) {
            /* Load Articles From Server */
            /* EDIT : ArticleDetail -> this */
        }


        /* FindViewById */
        tbWrite = findViewById(R.id.toolbar_write);
        ivCamera = findViewById(R.id.iv_camera_write);
        ivMedia = findViewById(R.id.iv_media_write);
        ivClip = findViewById(R.id.iv_clip_write);
        ivLocation = findViewById(R.id.iv_location_write);
        ivLink = findViewById(R.id.iv_link_write);
        ivEmoji = findViewById(R.id.iv_emoji_write);
        ivStorage = findViewById(R.id.iv_storage_write);
        ivSetting = findViewById(R.id.iv_setting_write);
        ivImg = findViewById(R.id.iv_img_write);
        btnSave = findViewById(R.id.btn_save_write);
        btnSubmit = findViewById(R.id.btn_submit_write);
        etTitle = findViewById(R.id.et_title_write);
        etContents = findViewById(R.id.et_contents_write);

        /* Toolbar */
        setSupportActionBar(tbWrite);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black);

        /* Set On Click Listener */
        ivCamera.setOnClickListener(this);
        ivMedia.setOnClickListener(this);
        ivClip.setOnClickListener(this);
        ivLocation.setOnClickListener(this);
        ivLink.setOnClickListener(this);
        ivEmoji.setOnClickListener(this);
        ivStorage.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        ivImg.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private int getSampleSize(BitmapFactory.Options options, InputStream in) {
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        int resultSampleSize = 1;
        int height = options.outHeight;
        int width = options.outWidth;
        while (height > MAXIMUM_IMG_HEIGHT || width > MAXIMUM_IMG_WIDTH) {
            resultSampleSize *= 2;
            height = height / 2;
            width = width / 2;
        }
        return resultSampleSize;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case REQUEST_FROM_ALBUM:
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = getSampleSize(options, in);
                    options.inJustDecodeBounds = false;
                    in.close(); // InputStream 재사용 불가
                    in = getContentResolver().openInputStream(data.getData());
                    bmIvImg = BitmapFactory.decodeStream(in, null, options);
                    in.close();
                    IMAGE_ATTACHED = true;
                    ivImg.setImageBitmap(bmIvImg);
                    ivImg.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_camera_write:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_FROM_ALBUM);
                break;
            case R.id.iv_media_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_clip_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_location_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_link_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_emoji_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_storage_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_setting_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.iv_img_write:
                // 이미지 삭제
                new AlertDialog.Builder(this)
                        .setTitle("사진")
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                IMAGE_ATTACHED = false;
                                ivImg.setVisibility(View.GONE);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();
                break;
            case R.id.btn_save_write:
                showToast(getString(R.string.nofunction));
                break;
            case R.id.btn_submit_write:
                // Server Connecting...
                if (!etTitle.getText().toString().equals("") && !etContents.getText().toString().equals(""))
                    postImageToFirebaseAndpostArticle(etTitle.getText().toString(), etContents.getText().toString(), "ANIBOARD", "anicafe");
                break;
        }
    }


    private void postArticle(String title, String contents, String categoryType, String cafeName, String imgUri) {
        final WriteService writeService = new WriteService(this);
        writeService.postArticle(title, contents, categoryType, cafeName, imgUri);
    }

    @Override
    public void validateUploadSuccess(String message) {
        hideProgressDialog();
        if (activityMode == MODE_CREATE)
            showToast("글 작성에 성공하였습니다.");
        else if (activityMode == MODE_EDIT)
            showToast("글 수정에 성공하였습니다.");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void validateUploadFailure(String message) {
        showToast((message == null) ? getString(R.string.network_error) : message);
    }

    private void postImageToFirebaseAndpostArticle(String title, String contents, String categoryType, String cafeName) {
        showUploadProgressDialog();
        final WriteService writeService = new WriteService(this);
        if (IMAGE_ATTACHED)
            writeService.postImgToFirebase(bmIvImg);
        else
            writeService.postArticle(title, contents, categoryType, cafeName, "");
    }

    @Override
    public void validateUploadImageSuccess(String url) {
        postArticle(etTitle.getText().toString(), etContents.getText().toString(), "ANIBOARD", "anicafe", url);
    }

    @Override
    public void validateUploadImageFailure(String message) {
        hideProgressDialog();
        showToast("이미지 업로드에 실패하였습니다.");
    }

    @Override
    public void validateDownloadSuccess(String message) {

    }

    @Override
    public void validateDownloadFailure(String message) {

    }
}
