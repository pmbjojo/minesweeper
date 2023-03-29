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

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.MineTileViewHolder> {
    private ArrayList<Cell> mData;
    private Cell[][]cells;
    private LayoutInflater layoutInflater;
    private OnCellClickListener listener;

    // data is passed into the constructor
    GridRecyclerViewAdapter(OnCellClickListener listener, Cell[][] cells) {
        this.cells = cells;
        this.listener = listener;
    }

    // inflates the row layout from xml when needed
    @Override
    public GridRecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_cell, parent, false);
        return new GridRecyclerViewAdapter(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(MineTileViewHolder holder, int position) {
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return cells.size();
    }




    // convenience method for getting data at click position
    Cell getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(OnCellClickListener cellClickListener) {
        this.listener = cellClickListener;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
        notifyDataSetChanged();
    }

    class MineTileViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;

        public MineTileViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.item_cell_value);
        }

        public void bind(final Cell cell) {
            itemView.setBackgroundColor(Color.GRAY);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.cellClick(cell);
                }
            });

            if (cell.isRevealed()) {
                if (cell.getValue() == Value.MINE) {
                    valueTextView.setText(R.string.mine);
                } else if (cell.getValue() == Value.BLANK) {
                    valueTextView.setText(R.string.blank);
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    valueTextView.setText(String.valueOf(cell.getValue()));
                    if (cell.getValue() == Value.ONE) {
                        valueTextView.setTextColor(Color.BLUE);
                    } else if (cell.getValue() == Value.TWO) {
                        valueTextView.setTextColor(Color.GREEN);
                    } else {
                        valueTextView.setTextColor(Color.RED);
                    }
                }
            } else if (cell.isFlagged()) {
                valueTextView.setText(R.string.flag);
            }
        }
    }
}

