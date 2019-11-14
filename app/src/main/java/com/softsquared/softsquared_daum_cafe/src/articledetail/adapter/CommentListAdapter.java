package com.softsquared.softsquared_daum_cafe.src.articledetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.Comment;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    private ArrayList<Comment> comments;
    private LayoutInflater layoutInflater;

    public CommentListAdapter(ArrayList<Comment> comments, Context context) {
        this.comments = comments;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_comment, parent, false);
        CommentViewHolder vh = new CommentViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        if (comment != null) {
            holder.tvUserName.setText(comment.getUserId());
            holder.tvCreatetime.setText(comment.getCreateAt());
            holder.tvComment.setText(comment.getContents());
            holder.ivNew.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserName;
        private TextView tvCreatetime;
        private TextView tvComment;
        private ImageView ivProfileImg;
        private ImageView ivNew;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tv_userid_comment);
            tvComment = itemView.findViewById(R.id.tv_comment_comment);
            tvCreatetime = itemView.findViewById(R.id.tv_createtime_comment);
            ivProfileImg = itemView.findViewById(R.id.iv_profile_img_comment);
            ivNew = itemView.findViewById(R.id.iv_new_comment);
        }
    }
}
