package com.example.minesweeper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.GridViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener listener;

    public GridRecyclerAdapter(List<Cell> cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.bind(cells.get(position), listener);
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder {
        private TextView cellText;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            cellText = itemView.findViewById(R.id.cellText);
        }

        public void bind(final Cell cell, final OnCellClickListener listener) {
            cellText.setText(cell.isRevealed() ? String.valueOf(cell.getAdjacentMines()) : "");
            cellText.setBackgroundResource(cell.isRevealed() ? R.drawable.revealed_cell : R.drawable.cell);
            if (cell.isFlagged()) {
                cellText.setText("F");
            }
            if (cell.isMine() && cell.isRevealed()) {
                cellText.setText("M");
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCellClick(cell);
                }
            });
        }
    }

}
