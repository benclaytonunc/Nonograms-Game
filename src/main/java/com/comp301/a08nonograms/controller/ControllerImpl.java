package com.comp301.a08nonograms.controller;

import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.Model;
import com.comp301.a08nonograms.model.Puzzle;
import com.comp301.a08nonograms.model.BoardImpl;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;

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
    if (model.getPuzzleIndex() >= model.getPuzzleCount()) {
      throw new RuntimeException();
    } else if (model.getPuzzleIndex() == 4) {
      model.setPuzzleIndex(0);
    } else {
      int index = model.getPuzzleIndex() + 1;
      model.setPuzzleIndex(index);
    }
  }

  @Override
  public void prevPuzzle() {
    if (model.getPuzzleIndex() < 0) {
      throw new RuntimeException();
    } else if (model.getPuzzleIndex() == 0) {
      model.setPuzzleIndex(4);
    } else {
      int index = model.getPuzzleIndex() - 1;
      model.setPuzzleIndex(index);
    }
  }

  @Override
  public void randPuzzle() {
    int random = (int) (Math.random() * (getPuzzleCount() + 1));
    model.setPuzzleIndex(random);
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
