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
import com.softsquared.softsquared_daum_cafe.src.articledetail.ArticleDetailActivity;
import com.softsquared.softsquared_daum_cafe.src.articledetail.ArticleDetailService;
import com.softsquared.softsquared_daum_cafe.src.articledetail.fragments.CommentBottomSheetDialogFragment;
import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.ArticleDetailActivityView;
import com.softsquared.softsquared_daum_cafe.src.articledetail.interfaces.CommentListAdapterView;
import com.softsquared.softsquared_daum_cafe.src.articledetail.models.Comment;

import java.util.ArrayList;

import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_EMAIL;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.USER_NAME;
import static com.softsquared.softsquared_daum_cafe.src.ApplicationClass.sSharedPreferences;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> implements CommentListAdapterView {

    private ArrayList<Comment> comments;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private CommentListAdapter mAdapter;
    private CommentBottomSheetDialogFragment mEditCommentBottomSheetDialogFragment;
    final ArticleDetailActivityView mArticleDetailActivityView;

    public CommentListAdapter(ArticleDetailActivityView articleDetailActivityView, ArrayList<Comment> comments, Context context) {
        mArticleDetailActivityView = articleDetailActivityView;
        this.comments = comments;
        this.layoutInflater = LayoutInflater.from(context);
        mContext = context;
        mAdapter = this;
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
        final Comment comment = comments.get(position);
        if (comment != null) {
            holder.tvUserName.setText(comment.getUserId());
            holder.tvCreatetime.setText(comment.getCreateAt());
            holder.tvComment.setText(comment.getContents());
            holder.ivNew.setVisibility(View.VISIBLE);

            if (sSharedPreferences.getString(USER_NAME, "").equals(comment.getUserId())
                    || sSharedPreferences.getString(USER_EMAIL, "").equals(comment.getUserId())) {
                holder.ivMore.setVisibility(View.VISIBLE);
                holder.ivMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditCommentBottomSheetDialogFragment = new CommentBottomSheetDialogFragment(mAdapter, comment.getId(), comment.getContents());
                        mEditCommentBottomSheetDialogFragment.show(((ArticleDetailActivity) mContext).getSupportFragmentManager(), null);
                    }
                });
            } else {
                holder.ivMore.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @Override
    public void onPatchClick(int cid, String cContents) {
        mArticleDetailActivityView.startCommentPatchProcessFromFragment(cid, cContents);
    }

    @Override
    public void onDeleteClick(int cid) {
        mArticleDetailActivityView.startCommentDeleteProcessFromFragment(cid);
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserName;
        private TextView tvCreatetime;
        private TextView tvComment;
        private ImageView ivProfileImg;
        private ImageView ivNew;
        private ImageView ivMore;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tv_userid_comment);
            tvComment = itemView.findViewById(R.id.tv_comment_comment);
            tvCreatetime = itemView.findViewById(R.id.tv_createtime_comment);
            ivProfileImg = itemView.findViewById(R.id.iv_profile_img_comment);
            ivNew = itemView.findViewById(R.id.iv_new_comment);
            ivMore = itemView.findViewById(R.id.iv_more_comment);
        }
    }
}
