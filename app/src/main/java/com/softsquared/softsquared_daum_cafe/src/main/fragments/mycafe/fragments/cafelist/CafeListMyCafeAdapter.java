package com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.softsquared_daum_cafe.R;
import com.softsquared.softsquared_daum_cafe.src.cafe.CafeActivity;
import com.softsquared.softsquared_daum_cafe.src.main.fragments.mycafe.fragments.cafelist.model.CafeListItem;

import java.util.ArrayList;

public class CafeListMyCafeAdapter extends RecyclerView.Adapter<CafeListMyCafeAdapter.MyCafeViewHolder> {

    private ArrayList<CafeListItem> cafeListItems;
    private LayoutInflater layoutInflater;

    public CafeListMyCafeAdapter(ArrayList<CafeListItem> cafeListItems, Context context) {
        this.cafeListItems = cafeListItems;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyCafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_mycafe, parent, false);

        MyCafeViewHolder vh = new MyCafeViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCafeViewHolder holder, int position) {

        CafeListItem cafeListItem = cafeListItems.get(position);

        if (cafeListItem != null) {
            holder.tvCafeTitle.setText(cafeListItem.getCafeTitle());
            holder.tvCafeDate.setText(cafeListItem.getCafeDate());
            if (cafeListItem.isFavorited()) {
                holder.ivStar.setImageResource(R.drawable.ic_star_full);
            } else {
                holder.ivStar.setImageResource(R.drawable.ic_star_border);
            }
            // Thumbnail 세팅
            // Date받아와서 newIcon 계산
        }
    }

    @Override
    public int getItemCount() {
        return cafeListItems.size();
    }

    public class MyCafeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivCafeThumbnail;
        private TextView tvCafeTitle;
        private TextView tvCafeDate;
        private ImageView ivStar;
        private ImageView ivNewIcon;

        public MyCafeViewHolder(@NonNull View itemView) {
            super(itemView);

            /* findViewByID */
            ivCafeThumbnail = itemView.findViewById(R.id.iv_thumbnail_item_mycafe);
            tvCafeTitle = itemView.findViewById(R.id.tv_name_item_mycafe);
            tvCafeDate = itemView.findViewById(R.id.tv_date_item_mycafe);
            ivStar = itemView.findViewById(R.id.iv_star_item_mycafe);
            ivNewIcon = itemView.findViewById(R.id.ic_newarticle_item_mycafe);

            /* Set on Click Listener */
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), CafeActivity.class);
            (v.getContext()).startActivity(intent);
        }
    }
}
