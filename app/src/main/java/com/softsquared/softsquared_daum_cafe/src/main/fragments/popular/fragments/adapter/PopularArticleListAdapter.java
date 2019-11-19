package com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.fragments.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.articledetail.ArticleDetailActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.popular.models.PopularResponse;

import java.util.ArrayList;

public class PopularArticleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEWTYPE_DEFAULT = 0;
    public static final int VIEWTYPE_IMAGE = 1;
    public static final int VIEWTYPE_NOIMAGE = 2;

    private ArrayList<PopularResponse.Result> articles;
    private LayoutInflater layoutInflater;
    private int viewType;

    private int lastAnimatedPosition = -1;
    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;

    public void setAnimationsLocked(boolean animationsLocked) {
        this.animationsLocked = animationsLocked;
    }

    public void setDelayEnterAnimation(boolean delayEnterAnimation) {
        this.delayEnterAnimation = delayEnterAnimation;
    }

    public PopularArticleListAdapter(ArrayList<PopularResponse.Result> articles, Context context, int viewType) {
        this.articles = articles;
        layoutInflater = LayoutInflater.from(context);
        this.viewType = viewType;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEWTYPE_IMAGE) {
            view = layoutInflater.inflate(R.layout.item_article_image_popular, parent, false);
            return new ArticleViewHolder(view);
        } else if (viewType == VIEWTYPE_NOIMAGE) {
            view = layoutInflater.inflate(R.layout.item_article_noimage_popular, parent, false);
            return new ArticleNoImageViewHolder(view);
        } else if (viewType == VIEWTYPE_DEFAULT) {
            view = layoutInflater.inflate(R.layout.item_article_default_popular, parent, false);
            return new ArticleDefaultViewHoler(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PopularResponse.Result article = articles.get(position);
        if (article != null) {
            if (holder instanceof ArticleViewHolder) {
                ((ArticleViewHolder) holder).tvNum.setText(String.format("%02d", position + 1));
                //((ArticleViewHolder) holder).tvCafeName.setText(article.getCafeName());
                ((ArticleViewHolder) holder).tvArticleTitle.setText(article.getTitle());
                //img
            } else if (holder instanceof ArticleDefaultViewHoler) {
                Log.i("DEFAULT", "DEFAULT");
            } else if (holder instanceof ArticleNoImageViewHolder) {
                ((ArticleNoImageViewHolder) holder).tvNum.setText(String.format("%02d", position + 1));
                //((ArticleNoImageViewHolder) holder).tvCafeName.setText(article.getCafeName());
                ((ArticleNoImageViewHolder) holder).tvArticleTitle.setText(article.getTitle());
            }
        }
        runEnterAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    private void runEnterAnimation(View view, int position) {
        if (animationsLocked)
            return;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(200);
            view.setAlpha(0.f);
            view.animate()
                    .translationY(0).alpha(1.f)
                    .setStartDelay(delayEnterAnimation ? 20 * position : 0)
                    .setInterpolator(new DecelerateInterpolator(2.f)).setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                        }
                    }).start();
        }
    }

    public class ArticleViewHolder extends ArticleNoImageViewHolder {

        ImageView ivImg;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNum = itemView.findViewById(R.id.tv_num_article_popular);
            tvArticleTitle = itemView.findViewById(R.id.tv_title_article_popular);
            tvCafeName = itemView.findViewById(R.id.tv_cafename_article_popular);
            ivImg = itemView.findViewById(R.id.iv_img_article_popular);

        }
    }

    public class ArticleDefaultViewHoler extends ArticleNoImageViewHolder {

        ImageView ivImg;

        public ArticleDefaultViewHoler(@NonNull View itemView) {
            super(itemView);

            tvNum = itemView.findViewById(R.id.tv_num_article_popular_default);
            tvArticleTitle = itemView.findViewById(R.id.tv_title_article_popular_default);
            tvCafeName = itemView.findViewById(R.id.tv_cafename_article_popular_default);
            ivImg = itemView.findViewById(R.id.iv_img_article_popular_default);
        }
    }

    public class ArticleNoImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNum;
        TextView tvArticleTitle;
        TextView tvCafeName;

        public ArticleNoImageViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNum = itemView.findViewById(R.id.tv_num_article_noimage_popular);
            tvArticleTitle = itemView.findViewById(R.id.tv_title_article_noimage_popular);
            tvCafeName = itemView.findViewById(R.id.tv_cafename_article_noimage_popular);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == itemView) {
                Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);
                v.getContext().startActivity(intent);
            }
        }
    }
}
