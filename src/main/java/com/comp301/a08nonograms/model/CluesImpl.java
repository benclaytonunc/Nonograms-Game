package com.comp301.a08nonograms.model;

public class CluesImpl implements Clues {
    private int[][] rowClues;
    private int[][] colClues;
    private int AmountShaded;

    public CluesImpl(int[][] rowClues, int[][] colClues) {
        if (rowClues == null || colClues == null) {
            throw new NullPointerException();
        }
        this.rowClues = rowClues;
        this.colClues = colClues;
        for (int[] rowClue : rowClues) {
            for (int i : rowClue) {
                AmountShaded = AmountShaded + i;
            }
        }
    }

    public int getShadedTotal() {
        return AmountShaded;
    }

    @Override
    public int getWidth() {
        return colClues.length;
    }

    @Override
    public int getHeight() {
        return rowClues.length;
    }

    @Override
    public int[] getRowClues(int index) {
        return rowClues[index];
    }

    @Override
    public int[] getColClues(int index) {
        return colClues[index];
    }

    @Override
    public int getRowCluesLength() {
       // return rowClues.length;
        return rowClues.length;
    }

    @Override
    public int getColCluesLength() {
     //   return colClues.length;
        return colClues.length;
    }
}