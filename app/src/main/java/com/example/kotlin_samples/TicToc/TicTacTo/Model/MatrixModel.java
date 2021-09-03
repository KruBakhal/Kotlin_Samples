package com.example.kotlin_samples.TicToc.TicTacTo.Model;

public class MatrixModel {

    int row, column, user = -1;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public MatrixModel(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "MatrixModel{" +
                "" + row +
                ",=" + column +
                '}';
    }
}
