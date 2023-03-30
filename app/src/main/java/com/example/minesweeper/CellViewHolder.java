package com.example.minesweeper;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CellViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    View cellView;
    public CellViewHolder(@NonNull View cellView) {
        super(cellView);
        this.cellView = cellView;
        this.textView = cellView.findViewById(R.id.tv_cell);
    }
    public void bind(final Cell cell) {
        cellView.setBackgroundColor(Color.GRAY);
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