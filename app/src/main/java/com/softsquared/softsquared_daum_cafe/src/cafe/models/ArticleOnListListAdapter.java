package com.softsquared.softsquared_daum_cafe.src.cafe.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;

import java.util.ArrayList;

public class ArticleOnListListAdapter extends RecyclerView.Adapter<ArticleOnListListAdapter.ArticleViewHoler> {

    private LayoutInflater layoutInflater;
    private ArrayList<ArticleOnList> articlesOnList;

    public ArticleOnListListAdapter(ArrayList<ArticleOnList> articlesOnList, Context context) {
        this.articlesOnList = articlesOnList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArticleViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_cafe_article, parent, false);

        ArticleViewHoler vh = new ArticleViewHoler(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHoler holder, int position) {
        ArticleOnList article = articlesOnList.get(position);
        if (article != null) {
            holder.tvTitle.setText(article.getTitle());
            holder.tvCreateDate.setText(article.getCreateDate());
            holder.tvAuthor.setText(article.getAuthor());
            holder.tvViewCount.setText("조회 " + article.getViewCount());
            holder.tvCommentCount.setText(article.getCommentCount());
            holder.tvBoard.setText(article.getBoard());
        }
    }

    @Override
    public int getItemCount() {
        return articlesOnList.size();
    }

    public class ArticleViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvTitle;
        private TextView tvCreateDate;
        private TextView tvAuthor;
        private TextView tvViewCount;
        private TextView tvCommentCount;
        private TextView tvBoard;

        public ArticleViewHoler(@NonNull View itemView) {
            super(itemView);

            /* findViewByID */
            tvTitle = itemView.findViewById(R.id.tv_article_title_articlelist);
            tvCreateDate = itemView.findViewById(R.id.tv_article_date_articlelist);
            tvAuthor = itemView.findViewById(R.id.tv_article_author_articlelist);
            tvViewCount = itemView.findViewById(R.id.tv_article_countview_articlelist);
            tvCommentCount = itemView.findViewById(R.id.tv_article_countcomment_articlelist);
            tvBoard = itemView.findViewById(R.id.tv_article_board_articlelist);

            /* Set On Click Listener */
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == itemView) {

            }
        }
    }
}
