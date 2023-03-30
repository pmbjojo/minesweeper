package com.example.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minesweeper.Cell;

import java.util.ArrayList;
import java.util.List;

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.MineViewHolder> {
    private LayoutInflater layoutInflater;
    private OnCellClickListener listener;
    private List<Cell> cells;

    // data is passed into the constructor
    GridRecyclerViewAdapter(OnCellClickListener listener, List<Cell> cells) {
        this.cells = cells;
        this.listener = listener;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    // inflates the row layout from xml when needed
    @Override
    public MineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_cell, parent, false);
        return new MineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MineViewHolder holder, int position) {
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    void setClickListener(OnCellClickListener cellClickListener) {
        this.listener = cellClickListener;
    }

    class MineViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MineViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_cell);
        }
        public void bind(final Cell cell) {
            itemView.setBackgroundColor(Color.GRAY);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCellClick(cell);
                }
            });

            if (cell.getReveal()) {
                if (cell.checkValue(Value.MINE)) {
                    textView.setText(R.string.mine);
                } else if (cell.checkValue(Value.BLANK)) {
                    textView.setText(R.string.blank);
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    textView.setText(String.valueOf(cell.getValue()));
                    if (cell.checkValue(Value.ONE)) {
                        textView.setTextColor(Color.BLUE);
                    } else if (cell.checkValue(Value.TWO)) {
                        textView.setTextColor(Color.GREEN);
                    } else {
                        textView.setTextColor(Color.RED);
                    }
                }
            } else if (cell.getFlag()) {
                textView.setText(R.string.flag);
            }
        }
    }
}

