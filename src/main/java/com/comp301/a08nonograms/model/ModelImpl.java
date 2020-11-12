package com.comp301.a08nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private List<Clues> clues;
    private ArrayList<Puzzle> puzzles;

    public Puzzle puzzle;
    private Clues clue;

    private Board board;
    private int Index;
    private List<ModelObserver> observers;

    public ModelImpl(List<Clues> clues) {
        this.puzzles = new ArrayList<>();
        for (int i = 0; i < clues.size(); i++) {
            puzzles.add(new Puzzle(clues.get(i), i));
        }
        this.puzzle = puzzles.get(0);
        this.Index = puzzle.getPuzzleIndex();
        this.clues = new ArrayList<>(clues);
        this.observers = new ArrayList<>();
        this.clue = clues.get(Index);
    }

    @Override
    public Clues getClue() {
        return clue;
    }


    @Override
    public int getPuzzleCount() {
        return puzzles.size();
    }

    @Override
    public int getPuzzleIndex() {
        return puzzle.getPuzzleIndex();
    }

    @Override
    public void setPuzzleIndex(int Index) {
        puzzle = puzzles.get(Index);
    }

    @Override
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public boolean isSolved() {
        if (puzzle.getBoard().getShadedTotal() != puzzle.getClue().getShadedTotal()) {
            return false;
        }
        for (int i = 0; i < getHeight(); i++) {
            int ClusterSize = 0;
            int clusterIndex = 0;
            boolean insideCluster = false;
            for (int k = 0; k < getWidth(); k++) {
                if (isSpace(i, k)) {
                    insideCluster = true;
                    if (ClusterSize > puzzle.getClue().getRowClues(i)[clusterIndex]) {
                        return false;
                    }
                    ClusterSize++;
                } else {
                    if (insideCluster) {
                        if (ClusterSize != puzzle.getClue().getRowClues(i)[clusterIndex]) {
                            return false;
                        } else {
                            insideCluster = false;
                            ClusterSize = 0;
                            clusterIndex++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < getWidth(); i++) {
            int ClusterSize = 0;
            int clusterIndex = 0;
            boolean insideCluster = false;
            for (int k = 0; k < getHeight(); k++) {
                if (isSpace(k, i)) {
                    return false;
                } else if (isShaded(k, i)) {
                    insideCluster = true;
                    if (ClusterSize > puzzle.getClue().getColClues(i)[clusterIndex]) {
                        return false;
                    }
                    ClusterSize++;
                } else {
                    if (insideCluster) {
                        if (ClusterSize != puzzle.getClue().getColClues(i)[clusterIndex]) {
                            return false;
                        } else {
                            insideCluster = false;
                            ClusterSize = 0;
                            clusterIndex++;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isShaded(int row, int col) {

        return puzzle.getBoard().isShaded(row, col);
    }

    @Override
    public boolean isEliminated(int row, int col) {
        return puzzle.getBoard().isEliminated(row, col);
    }

    @Override
    public boolean isSpace(int row, int col) {
        return puzzle.getBoard().isSpace(row, col);
    }

    @Override
    public void toggleCellShaded(int row, int col) {
        puzzle.getBoard().toggleCellShaded(row, col);
        notifyObservers();
    }

    @Override
    public void toggleCellEliminated(int row, int col) {
        puzzle.getBoard().toggleCellEliminated(row, col);
        notifyObservers();
    }

    @Override
    public void clear() {
        puzzle.getBoard().clear();
        notifyObservers();
    }

    @Override
    public int getWidth() {
        return puzzle.getClue().getWidth();
    }

    @Override
    public int getHeight() {
        return puzzle.getClue().getHeight();
    }

    @Override
    public int[] getRowClues(int Index) {
        return puzzle.getClue().getRowClues(Index);
    }

    @Override
    public int[] getColClues(int Index) {
        return puzzle.getClue().getColClues(Index);
    }

    @Override
    public int getRowCluesLength() {
        return puzzle.getClue().getRowCluesLength();
    }

    @Override
    public int getColCluesLength() {
        return puzzle.getClue().getColCluesLength();
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.update(this);
        }
    }
}
