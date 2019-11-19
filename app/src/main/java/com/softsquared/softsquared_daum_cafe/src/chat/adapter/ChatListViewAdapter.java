package com.softsquared.softsquared_daum_cafe.src.chat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.chat.models.ChatRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class ChatListViewAdapter extends RecyclerView.Adapter {

    public static final int VIEWTYPE_MINE = 0;
    public static final int VIEWTYPE_OTHERS = 1;


    private ArrayList<ChatRequest> chatRequests;
    private LayoutInflater layoutInflater;

    public ChatListViewAdapter(ArrayList<ChatRequest> chatRequests, Context context) {
        this.chatRequests = chatRequests;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void addChat(ChatRequest chat) {
        chatRequests.add(chat);
        notifyItemInserted(chatRequests.size() - 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEWTYPE_MINE) {
            view = layoutInflater.inflate(R.layout.item_chat_my, parent, false);
            return new MyChatViewHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.item_chat_others, parent, false);
            return new OtherChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatRequest chatRequest = chatRequests.get(position);
        SimpleDateFormat simpleDateFormat;
        Log.i("LOCALE", String.valueOf(Locale.getDefault()));
        if (Locale.getDefault().equals(Locale.KOREAN) || Locale.getDefault().equals(Locale.KOREA))
            simpleDateFormat = new SimpleDateFormat("MMM d일 HH:mm", Locale.KOREA);
        else
            simpleDateFormat = new SimpleDateFormat("d MMM HH:mm", Locale.ENGLISH);
        if (holder instanceof MyChatViewHolder) {
            ((MyChatViewHolder) holder).tvCreatedAt.setText(simpleDateFormat.format(new Date(chatRequest.getTime())));
            ((MyChatViewHolder) holder).tvContents.setText(chatRequest.getContent());
        } else {
            ((OtherChatViewHolder) holder).tvCreatedAt.setText(simpleDateFormat.format(new Date(chatRequest.getTime())));
            ((OtherChatViewHolder) holder).tvContents.setText(chatRequest.getContent());
            ((OtherChatViewHolder) holder).tvUserName.setText(chatRequest.getUserName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatRequests.get(position).getUserName().equals(sSharedPreferences.getString(USER_NAME, "")))
            return VIEWTYPE_MINE;
        else
            return VIEWTYPE_OTHERS;
    }

    @Override
    public int getItemCount() {
        return chatRequests.size();
    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContents;
        private TextView tvCreatedAt;

        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);

            tvContents = itemView.findViewById(R.id.tv_content_chat_my);
            tvCreatedAt = itemView.findViewById(R.id.tv_time_chat_my);
        }
    }

    public class OtherChatViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContents;
        private TextView tvCreatedAt;
        private TextView tvUserName;

        public OtherChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContents = itemView.findViewById(R.id.tv_content_chat_others);
            tvCreatedAt = itemView.findViewById(R.id.tv_time_chat_others);
            tvUserName = itemView.findViewById(R.id.tv_username_chat_others);
        }
    }
}
