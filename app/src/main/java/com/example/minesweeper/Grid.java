package com.example.minesweeper;

public class Grid {
    private Cell[][] grid;
    private int rows;
    private int cols;
    private int mines;
    public Grid(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
        placeMines();
        calculateAdjacentMines();
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMines() {
        return mines;
    }

    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isMine()) {
                    int adjacentMines = 0;
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k >= 0 && k < rows && l >= 0 && l < cols) {
                                if (grid[k][l].isMine()) {
                                    adjacentMines++;
                                }
                            }
                        }
                    }
                    grid[i][j].setAdjacentMines(adjacentMines);
                }
            }
        }
    }
}
