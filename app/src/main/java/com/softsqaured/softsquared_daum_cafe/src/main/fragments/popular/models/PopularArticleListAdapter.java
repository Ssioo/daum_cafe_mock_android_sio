package com.softsqaured.softsquared_daum_cafe.src.main.fragments.popular.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsqaured.softsquared_daum_cafe.R;

import java.util.ArrayList;

public class PopularArticleListAdapter extends RecyclerView.Adapter<PopularArticleListAdapter.ArticleViewHolder> {

    private ArrayList<Article> articles;
    private LayoutInflater layoutInflater;

    public PopularArticleListAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_article_popular, parent, false);

        ArticleViewHolder viewHolder = new ArticleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        if (article != null) {
            holder.tvNum.setText(String.format("%02d", position + 1));
            holder.tvCafeName.setText(article.getCafeName());
            holder.tvArticleTitle.setText(article.getArticleTitle());
            //img
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvNum;
        TextView tvArticleTitle;
        TextView tvCafeName;
        ImageView ivImg;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNum = itemView.findViewById(R.id.tv_num_article_popular);
            tvArticleTitle = itemView.findViewById(R.id.tv_title_article_popular);
            tvCafeName = itemView.findViewById(R.id.tv_cafename_article_popular);
            ivImg = itemView.findViewById(R.id.iv_img_article_popular);
        }
    }
}
