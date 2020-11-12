package com.comp301.a08nonograms.controller;

import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.Model;
import com.comp301.a08nonograms.model.Puzzle;


public class ControllerImpl implements Controller {
    private Model model;
    private Clues clues;
    private Puzzle puzzle;

    public ControllerImpl(Model model) {
        this.model = model;
    }

    @Override
    public boolean isSolved() {
        return model.isSolved();
    }

    @Override
    public boolean isShaded(int row, int col) {
        return model.isShaded(row, col);
    }

    @Override
    public Clues getClues() {
        return model.getClue();
    }

    @Override
    public boolean isEliminated(int row, int col) {
        return model.isEliminated(row, col);
    }

    @Override
    public void toggleShaded(int row, int col) {
        model.toggleCellShaded(row, col);
    }

    @Override
    public void toggleEliminated(int row, int col) {
        model.toggleCellEliminated(row, col);
    }

    @Override
    public void nextPuzzle() {
        model.setPuzzleIndex(getPuzzleIndex() + 1);
    }

    @Override
    public void prevPuzzle() {
        model.setPuzzleIndex(getPuzzleIndex() - 1);
    }

    @Override
    public void randPuzzle() {
        model.setPuzzleIndex((int) (Math.random() * (getPuzzleCount() + 1)));
    }

    @Override
    public void clearBoard() {
        model.clear();
    }

    @Override
    public int getPuzzleIndex() {
        return model.getPuzzleIndex();
    }

    @Override
    public int getPuzzleCount() {
        return model.getPuzzleCount();
    }
}