package com.comp301.a08nonograms.model;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardImpl implements Board {

  public enum select {
    SPACE,
    SHADED,
    ELIMINATED
  }

  private int AmountEliminated;
  private int amountShaded;
  public select[][] board;

  private int numberOfRows;
  private int numberOfColumns;

  public BoardImpl(int numberOfColumns, int numberOfRows) {
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.board = new select[numberOfRows][numberOfColumns];
    clear();
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row >= numberOfRows || row < 0 || col >= numberOfColumns || col < 0) {
      throw new RuntimeException();
    }
    return (board[row][col] == select.SHADED);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row >= numberOfRows || row < 0 || col >= numberOfColumns || col < 0) {
      throw new RuntimeException();
    }
    return board[row][col] == select.ELIMINATED;
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row >= numberOfRows || row < 0 || col >= numberOfColumns || col < 0) {
      throw new RuntimeException();
    }
    return board[row][col] == select.SPACE;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row >= numberOfRows || row < 0 || col >= numberOfColumns || col < 0) {
      throw new RuntimeException();
    }
    if (board[row][col] == select.SHADED) {
      board[row][col] = select.SPACE;
    } else if (board[row][col] == select.SPACE) {
   //   board[row][col] = select.SPACE;
      board[row][col] = select.SHADED;
      amountShaded++;
    } else {
      board[row][col] = select.SHADED;
      AmountEliminated--;
      amountShaded++;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row >= numberOfRows || row < 0 || col >= numberOfColumns || col < 0) {
      throw new RuntimeException();
    }
    if (board[row][col] == select.ELIMINATED) {
      board[row][col] = select.SPACE;
      // potential error
      AmountEliminated--;
    } else if (board[row][col] == select.SPACE) {
      board[row][col] = select.ELIMINATED;
      AmountEliminated++;
    } else {
      board[row][col] = select.SHADED;
      AmountEliminated--;
      amountShaded++;
    }
  }

  public int getShadedTotal() {
    return amountShaded;
  }

  public int getEliminatedTotal() {
    return AmountEliminated;
  }

  @Override
  public void clear() {
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {
        board[i][j] = select.SPACE;
      }
    }
  }
}
