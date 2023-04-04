package com.example.minesweeper;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RankViewHolder extends RecyclerView.ViewHolder {
    public View rankView;
    public TextView tvDate;
    public TextView tvTime;
    public RankViewHolder(View rankView) {
        super(rankView);
        this.rankView = rankView;
        this.tvTime = rankView.findViewById(R.id.tv_time);
        this.tvDate = rankView.findViewById(R.id.tv_date);
    }
}

