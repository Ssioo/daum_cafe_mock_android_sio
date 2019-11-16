package com.softsquared.softsquared_daum_cafe.src.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_ID;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_LOGINNED;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class ChatActivity extends BaseActivity implements ChatActivityView {

    private DatabaseReference fdrChat;

    private EditText etInputChat;
    private RecyclerView rvChat;
    private TextView tvSubmitChat;
    private Toolbar tbChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /* FindViewByID */
        etInputChat = findViewById(R.id.et_input_chat);
        rvChat = findViewById(R.id.rv_chat_chat);
        tbChat = findViewById(R.id.toolbar_chat);
        tvSubmitChat = findViewById(R.id.tv_submit_chat);

        /* Toolbar */
        setSupportActionBar(tbChat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_black);
        actionBar.setDisplayShowTitleEnabled(false);

        /* RecyclerView */
        final ChatListViewAdapter chatListViewAdapter = new ChatListViewAdapter(new ArrayList<ChatRequest>(), this);
        rvChat.setAdapter(chatListViewAdapter);

        /* Firebase Database Reference */
        fdrChat = chatDatabase.child("anicafe");
        fdrChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatRequest chatRequest = dataSnapshot.getValue(ChatRequest.class);
                chatListViewAdapter.addChat(chatRequest);
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
                ChatRequest chatRequest = new ChatRequest(sSharedPreferences.getString(USER_ID, ""), etInputChat.getText().toString(), createdAt.getTime());
                fdrChat.push().setValue(chatRequest);
                etInputChat.setText("");
                break;
        }
    }
}
