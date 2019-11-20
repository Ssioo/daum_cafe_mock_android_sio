package com.softsquared.softsquared_daum_cafe.src.cafe.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.cafe.models.Category;

import java.util.ArrayList;

public class CafeCategoryListAdapter extends RecyclerView.Adapter<CafeCategoryListAdapter.CategoryViewHolder> {

    private ArrayList<Category> categories;
    private LayoutInflater layoutInflater;

    public CafeCategoryListAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_boardlist_cafe, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        if (category != null) {
            holder.tvTitle.setText(category.getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivStar;
        ImageView ivType;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title_category);
            ivStar = itemView.findViewById(R.id.iv_star_category);
            ivType = itemView.findViewById(R.id.iv_type_category);
        }
    }
}
