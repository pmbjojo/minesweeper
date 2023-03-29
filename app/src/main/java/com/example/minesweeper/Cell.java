package com.example.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Cell extends Fragment {
    private Value value;
    private boolean flag;
    private boolean reveal;
    public Cell() {
        clear();
    }
    public Cell(Value value) {
        setValue(value);
        setFlag(false);
        setReveal(false);
    }
    public Value getValue() {
        return value;
    }
    public void setValue(Value value) {
        this.value = value;
    }
    public boolean getFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public boolean getReveal() {
        return reveal;
    }
    public void setReveal(boolean reveal) {
        this.reveal = reveal;
    }
    public void clear() {
        setValue(Value.BLANK);
        setFlag(false);
        setReveal(false);
    }
    public boolean checkValue(Value value) {
        return value == getValue();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cell, container, false);
    }

}