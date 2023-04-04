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
        switch (cell.getType()) {
            case REVEAL:
                if (cell.checkValue(Value.MINE)) {
                    textView.setText(R.string.mine);
                } else if (cell.checkValue(Value.BLANK)) {
                    textView.setText(R.string.blank);
                } else {
                    textView.setText(Integer.toString(cell.getValue().ordinal()));
                    cellView.setBackgroundColor(Color.WHITE);
                    if (cell.checkValue(Value.ONE)) {
                        textView.setTextColor(Color.BLUE);
                    } else if (cell.checkValue(Value.TWO)) {
                        textView.setTextColor(Color.GREEN);
                    } else {
                        textView.setTextColor(Color.RED);
                    }
                }
                textView.setBackgroundColor(Color.WHITE);
                break;
            case FLAG:
                textView.setText(R.string.flag);
                textView.setBackgroundColor(Color.WHITE);
            case HIDE:
                textView.setBackgroundColor(Color.GRAY);

        }
    }
}