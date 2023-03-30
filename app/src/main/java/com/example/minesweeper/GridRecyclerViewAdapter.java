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

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<CellViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener listener;
    public GridRecyclerViewAdapter(List<Cell> cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }
    @Override
    public CellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_cell, parent, false);
        return new CellViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CellViewHolder viewHolder, int position) {
        viewHolder.bind(this.cells.get(position));
    }
    @Override
    public int getItemCount() {
        return this.cells.size();
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
        notifyDataSetChanged();
    }

    public List<Cell> getCells() {
        return cells;
    }
}