package com.example.minesweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<CellViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener listener;

    public GridAdapter(List<Cell> cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }

    @Override
    public CellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cell, parent, false);
        return new CellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CellViewHolder viewHolder, int position) {
        viewHolder.bind(this.cells.get(position));
        viewHolder.itemView.setOnClickListener(v -> {
            listener.onCellClick(cells.get(position));
            setCells(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.cells.size();
    }

    public void setCells(int index) {
        notifyDataSetChanged();
    }

    public List<Cell> getCells() {
        return cells;
    }
}
