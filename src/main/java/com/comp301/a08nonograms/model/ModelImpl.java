package com.comp301.a08nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private List<Clues> cluesPuzz;
  private Board board;
  private int PuzzleIndex;
  private List<ModelObserver> observers;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new RuntimeException("no clues!");
    }

    this.cluesPuzz = new ArrayList<>();
    for (int i = 0; i < clues.size(); i++) {
      cluesPuzz.add(clues.get(i));
    }
    this.PuzzleIndex = 0;
    // cluesPuzz.addAll(clues);
    this.observers = new ArrayList<>();
    this.board = new BoardImpl(clues.get(0).getWidth(), clues.get(0).getHeight());
  }

  @Override
  public Clues getClue() {
    return cluesPuzz.get(getPuzzleIndex());
  }

  @Override
  public int getPuzzleCount() {
    return cluesPuzz.size();
  }

  @Override
  public int getPuzzleIndex() {
    return PuzzleIndex;
  }

  @Override
  public void setPuzzleIndex(int Index) {
    if (Index > cluesPuzz.size() || Index < 0) {
      throw new RuntimeException("set puzzle index OOB");
    }
    this.PuzzleIndex = Index;
    board =
        new BoardImpl(
            cluesPuzz.get(PuzzleIndex).getWidth(), cluesPuzz.get(PuzzleIndex).getHeight());
    notifyObservers();
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
    Clues clews = cluesPuzz.get(PuzzleIndex);
    int[] cols = new int[clews.getWidth()];
    int[] rows = new int[clews.getHeight()];
    for (int i = 0; i < clews.getHeight(); i++) {
      for (int j = 0; j < clews.getRowCluesLength(); j++) {
        rows[i] += clews.getRowClues(i)[j];
      }
    }
    for (int x = 0; x < clews.getHeight(); x++) {
      int rowTotal = 0;
      for (int y = 0; y < clews.getWidth(); y++) {
        if (board.isShaded(x, y)) {
          rowTotal += 1;
        }
      }
      if (rowTotal != rows[x]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isShaded(int row, int col) {
    return board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    board.toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    board.toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    board.clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return cluesPuzz.get(PuzzleIndex).getWidth();
  }

  @Override
  public int getHeight() {
    return cluesPuzz.get(PuzzleIndex).getHeight();
  }

  @Override
  public int[] getRowClues(int Index) {
    return cluesPuzz.get(PuzzleIndex).getRowClues(Index);
  }

  @Override
  public int[] getColClues(int Index) {
    return cluesPuzz.get(PuzzleIndex).getColClues(Index);
  }

  @Override
  public int getRowCluesLength() {
    return cluesPuzz.get(PuzzleIndex).getRowCluesLength();

    // return puzzle.getClue();
  }

  @Override
  public int getColCluesLength() {
    return cluesPuzz.get(PuzzleIndex).getColCluesLength();
    // return puzzle.getClue().getWidth();
  }

  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}
