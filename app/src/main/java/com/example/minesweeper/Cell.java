package com.example.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cell#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cell extends Fragment {
    private boolean isMine;
    private boolean isFlagged;
    private boolean isRevealed;
    private int adjacentMines;

    public Cell() {
        isMine = false;
        isFlagged = false;
        isRevealed = false;
        adjacentMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }
    public boolean isFlagged() {
        return isFlagged;
    }
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
    public boolean isRevealed() {
        return isRevealed;
    }
    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }
    public int getAdjacentMines() {
        return adjacentMines;
    }
    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
    public static Cell newInstance(String param1, String param2) {
        Cell fragment = new Cell();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cell, container, false);
    }
}