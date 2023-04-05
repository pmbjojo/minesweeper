package com.example.minesweeper;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<CellViewHolder> {
    int cellSize;
    private List<Cell> cells;
    private OnCellClickListener listener;

    public GridAdapter(List<Cell> cells, OnCellClickListener listener, int cellSize) {
        this.cells = cells;
        this.listener = listener;
        this.cellSize = cellSize;
    }

    @Override
    public CellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cell, parent, false);

        TextView cellTextView = view.findViewById(R.id.tv_cell);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 2, view.getResources().getDisplayMetrics());
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, cellSize, view.getResources().getDisplayMetrics());
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, cellSize / 2, view.getResources().getDisplayMetrics());
        view.setPadding(padding, padding, padding, padding);
        cellTextView.setPadding(padding, padding, padding, padding);
        cellTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
        cellTextView.setLayoutParams(params);
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

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(int index) {
        notifyDataSetChanged();
    }
}
