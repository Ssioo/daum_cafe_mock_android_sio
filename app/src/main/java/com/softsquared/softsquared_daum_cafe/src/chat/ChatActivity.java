package com.softsquared.softsquared_daum_cafe.src.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.BaseActivity;
import com.softsquared.softsquared_daum_cafe.src.chat.adapter.ChatListViewAdapter;
import com.softsquared.softsquared_daum_cafe.src.chat.interfaces.ChatActivityView;
import com.softsquared.softsquared_daum_cafe.src.chat.models.ChatRequest;

import java.util.ArrayList;
import java.util.Date;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class ChatActivity extends BaseActivity implements ChatActivityView {

    private DatabaseReference fdrChat;

    private EditText etInputChat;
    private RecyclerView rvChat;
    private TextView tvSubmitChat;
    private Toolbar tbChat;

    private String mCafeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /* FindViewByID */
        etInputChat = findViewById(R.id.et_input_chat);
        rvChat = findViewById(R.id.rv_chat_chat);
        tbChat = findViewById(R.id.toolbar_chat);
        tvSubmitChat = findViewById(R.id.tv_submit_chat);

        /* Get Intent */
        Intent intent = getIntent();
        mCafeName = intent.getStringExtra("cafeName");

        /* Toolbar */
        setSupportActionBar(tbChat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black);
        actionBar.setDisplayShowTitleEnabled(false);

        /* RecyclerView */
        final ChatListViewAdapter chatListViewAdapter = new ChatListViewAdapter(new ArrayList<ChatRequest>(), this);
        rvChat.setAdapter(chatListViewAdapter);
        rvChat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Log.i(TAG, bottom + ", " + oldBottom);
                if (bottom < oldBottom) {
                    rvChat.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int position = chatListViewAdapter.getItemCount() - 1;
                            if (position < 0)
                                position = 0;
                            rvChat.smoothScrollToPosition(position);
                        }
                    }, 100);
                }
            }
        });

        /* Firebase Database Reference */
        fdrChat = chatDatabase.child(mCafeName);
        fdrChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatRequest chatRequest = dataSnapshot.getValue(ChatRequest.class);
                chatListViewAdapter.addChat(chatRequest);
                rvChat.scrollToPosition(chatListViewAdapter.getItemCount() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /* Set On Click Listenr */
        tvSubmitChat.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit_chat:
                Date createdAt = new Date();
                ChatRequest chatRequest = new ChatRequest(sSharedPreferences.getString(USER_NAME, ""), etInputChat.getText().toString(), createdAt.getTime());
                fdrChat.push().setValue(chatRequest);
                etInputChat.setText("");
                break;
        }
    }

}
