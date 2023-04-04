package com.example.minesweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankViewHolder> {
    private List<String> itemList;

    public RankingAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rank, parent, false);
        return new RankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.tvDate.setText(item.split("_")[0]);
        holder.tvTime.setText(item.split("_")[1]);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
