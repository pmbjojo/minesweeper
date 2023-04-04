package com.example.minesweeper;

public class Cell {
    private Value value;
    public static int size = 30;
    private OnCellClickListener listener;
    private Type type;

    public Cell() {
        clear();

    }

    public Cell(Value value) {
        setValue(value);
        setType(Type.HIDE);
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void clear() {
        setValue(Value.BLANK);
        setType(Type.HIDE);
    }

    public boolean checkValue(Value value) {
        return value == getValue();
    }

    public boolean checkType(Type type) {
        return type == getType();
    }
}