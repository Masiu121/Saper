package com.oxology.saper.util;

public enum Difficulty {
    EASY(20, 20, 0.2f),
    MEDIUM(40, 40, 0.4f),
    HARD(50, 50, 0.6f);

    private int cols, rows;
    private float bombChance;

    Difficulty(int cols, int rows, float bombChance) {
        this.cols = cols;
        this.rows = rows;
        this.bombChance = bombChance;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public float getBombChance() {
        return bombChance;
    }
}
