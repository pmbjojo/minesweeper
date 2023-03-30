package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Grid grid;
    private int reveal;
    private boolean over;
    private boolean won;
    private boolean flag;
    private int flags;
    private boolean time;
    private OnCellClickListener onCellClickListener;
    public GameEngine(Difficulty difficulty) {
        setGrid(new Grid(difficulty));
        //setFlags(grid.getMines());
        //setReveal(0);
        //setWon(false);
        //setOver(false);
        //setFlag(false);
    }
    public void flag(Cell cell) {
        cell.setFlag(!cell.getFlag());
        int count = 0;
        for(int i = 0; i < grid.getRows(); i++) {
            for(int j = 0; j < grid.getColumns(); j++) {
                if(grid.getGrid()[i][j] == cell) {
                    if(grid.getGrid()[i][j].getFlag()) {
                        count++;
                    }
                }
            }
        }
        setFlags(count);
    }
    public boolean isGameWon() {
        int unreveal = 0;
        for(int i = 0; i < grid.getRows(); i++) {
            for(int j = 0; j < grid.getColumns(); j++) {
                if(!grid.getGrid()[i][j].checkValue(Value.MINE) && !grid.getGrid()[i][j].checkValue(Value.BLANK) && !grid.getGrid()[i][j].getReveal()) {
                    unreveal++;
                }
            }
        }
        return unreveal == 0;
    }
    public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
        this.onCellClickListener = onCellClickListener;
    }
    public OnCellClickListener getOnCellClickListener() {
        return onCellClickListener;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean getFlag() {
        return flag;
    }
    public int getFlags() {
        return flags;
    }
    public int getReveal() {
        return reveal;
    }
    public boolean getOver() {
        return over;
    }

    public boolean getWon() {
        return won;
    }
    public Grid getGrid() {
        return grid;
    }
    public void setTime(boolean time) {
        this.time = time;
    }
    public boolean getTime() {
        return time;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
    public void setOver(boolean over) {
        this.over = over;
    }
    public void setReveal(int reveal) {
        this.reveal = reveal;
    }
    public void setFlags(int flags) {
        this.flags = flags;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}