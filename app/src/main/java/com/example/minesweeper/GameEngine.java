package com.example.minesweeper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Grid grid;
    private int reveal;
    private Status status;
    private Result result;
    private Mode mode;
    private int flags;
    private OnCellClickListener onCellClickListener;
    private Service timer;
    private Context context;

    public GameEngine(Context context, Intent serviceTimer, Difficulty difficulty) {
        setGrid(new Grid(difficulty));
        setFlags(grid.getMines());
        setReveal(0);
        setRunning();
        setMode(Mode.CLEAR);
        setContext(context);
        getContext().startService(serviceTimer);
    }

    public void switchMode() {
        switch (getMode()) {
            case FLAG:
                setMode(Mode.CLEAR);
                break;
            case CLEAR:
                setMode(Mode.FLAG);
                break;
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void handleMode(Cell cell) {
        switch (getMode()) {
            case FLAG:
                flag(cell);
                break;
            case CLEAR:
                clear(cell);
                break;
        }
    }

    public void flag(Cell cell) {
        switch (cell.getType()) {
            case FLAG:
                cell.setType(Type.HIDE);
                setFlags(getFlags() + 1);
                break;
            case HIDE:
                if (getFlags() > 0) {
                    cell.setType(Type.FLAG);
                    setFlags(getFlags() - 1);
                }
                break;
        }
    }

    public OnCellClickListener getOnCellClickListener() {
        return onCellClickListener;
    }

    public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
        this.onCellClickListener = onCellClickListener;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getReveal() {
        return reveal;
    }

    public void setReveal(int reveal) {
        this.reveal = reveal;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRunning() {
        setStatus(Status.RUNNING);
    }

    public void setOver() {
        setStatus(Status.OVER);
    }

    public void setWin() {
        setResult(Result.WIN);
    }

    public void setLose() {
        setResult(Result.LOSE);
    }

    public void clear(Cell cell) {
        switch (cell.getValue()) {
            case MINE:
                cell.setType(Type.REVEAL);
                setLose();
                setOver();
                break;
            case BLANK:
                List<Cell> toReveal = new ArrayList<>();
                List<Cell> toCheck = new ArrayList<>();
                toCheck.add(cell);
                toReveal.add(cell);
                while (toCheck.size() > 0) {
                    Cell c = toCheck.get(0);
                    int[] cellIndex = grid.getXY(c);
                    for (Cell adjacent : grid.adjacents(cellIndex[0], cellIndex[1])) {
                        if (adjacent.checkValue(Value.BLANK)) {
                            if (!toReveal.contains(adjacent)) {
                                if (!toCheck.contains(adjacent)) {
                                    toCheck.add(adjacent);
                                }
                            }
                        } else {
                            if (!toReveal.contains(adjacent)) {
                                toReveal.add(adjacent);
                            }
                        }
                    }
                    toCheck.remove(c);
                    toReveal.add(c);
                }
                for (Cell c : toReveal) {
                    if (!c.checkType(Type.REVEAL)) {
                        c.setType(Type.REVEAL);
                        setReveal(getReveal() + 1);
                    }
                }
                //Toast.makeText(getContext(), "To reveal : " + grid.getRevealNumber() + "Revealed" + getReveal(), Toast.LENGTH_SHORT).show();
                break;
            default:
                if (!cell.checkType(Type.REVEAL)) {
                    cell.setType(Type.REVEAL);
                    setReveal(getReveal() + 1);
                }
                //Toast.makeText(getContext(), "To reveal : " + grid.getRevealNumber() + "Revealed" + getReveal(), Toast.LENGTH_SHORT).show();
                break;
        }
        if (getGrid().getRevealNumber() == getReveal()) {
            setWin();
            setOver();
        }
    }
}