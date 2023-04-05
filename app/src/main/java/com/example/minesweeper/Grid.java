package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    private static final int EASY[] = {8, 8, 1};
    private static final int MEDIUM[] = {12, 12, 25};
    private static final int HARD[] = {16, 16, 40};
    Difficulty difficulty;
    private Cell[][] grid;
    private int rows;
    private int columns;
    private int mines;

    Grid(Difficulty difficulty) {
        setDifficulty(difficulty);
        initGrid();
        initMines();
    }

    public void initGrid() {
        setGrid(new Cell[getRows()][getColumns()]);
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                getGrid()[x][y] = new Cell();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void clearGrid() {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                getGrid()[x][y].clear();
            }
        }
    }

    public void initMines() {
        int minesPlaced = 0;
        while (minesPlaced < getMines()) {
            int x = new Random().nextInt(getRows());
            int y = new Random().nextInt(getColumns());
            if (getGrid()[x][y].checkValue(Value.BLANK)) {
                getGrid()[x][y].setValue(Value.MINE);
                minesPlaced++;
            }
        }

        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                if (!getGrid()[x][y].checkValue(Value.MINE)) {
                    setCount(x, y);
                }
            }
        }
    }

    public List<Cell> adjacents(int x, int y) {
        List<Cell> adjacentsCells = new ArrayList<Cell>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < getRows() && j >= 0 && j < getColumns()) {
                    if (grid[i][j] != null) {
                        adjacentsCells.add(grid[i][j]);
                    }
                }
            }
        }
        return adjacentsCells;
    }

    private void setCount(int x, int y) {
        int countMines = 0;
        for (Cell cell : adjacents(x, y)) {
            if (cell.checkValue(Value.MINE)) {
                countMines++;
            }
            if (countMines > 0) {
                grid[x][y].setValue(Value.values()[countMines]);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY:
                setRows(EASY[0]);
                setColumns(EASY[1]);
                setMines(EASY[2]);
                break;
            case MEDIUM:
                setRows(MEDIUM[0]);
                setColumns(MEDIUM[1]);
                setMines(MEDIUM[2]);
                break;
            case HARD:
                setRows(HARD[0]);
                setColumns(HARD[1]);
                setMines(HARD[2]);
                break;
        }
    }

    public List<Cell> getCellList() {
        List<Cell> cellList = new ArrayList<Cell>();
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                cellList.add(getGrid()[x][y]);
            }
        }
        return cellList;
    }

    public int[] getXY(Cell toFind) {
        int value[] = {-1, -1};
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getColumns(); y++) {
                if (getGrid()[x][y] == toFind) {
                    value = new int[]{x, y};
                }
            }
        }
        return value;
    }

    public int getItemNumber() {
        return getRows() * getColumns();
    }

    public int getRevealNumber() {
        return getItemNumber() - getMines();
    }
}
