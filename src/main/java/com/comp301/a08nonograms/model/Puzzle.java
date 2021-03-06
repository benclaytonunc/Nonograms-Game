package com.comp301.a08nonograms.model;

public class Puzzle {
  private CluesImpl clue;
  private BoardImpl board;
  private int index;

  public Puzzle(Clues clues, int index) {
    this.clue = (CluesImpl) clues;
    this.board = new BoardImpl(clues.getColCluesLength(), clue.getRowCluesLength());
    this.index = index;
  }
  /*
    public int getPuzzleIndex() {
      if (index > 4 | index < 0 ) {
        throw new RuntimeException("get puzzle index OOB");
      }
      return index;
    }

    public void setPuzzleIndex(int index) {
      if (index >= 4 | index < 0 ) {
        throw new RuntimeException("set puzzle index OOB");
      }
      this.index = index;
    }
  */
  public BoardImpl getBoard() {
    return board;
  }

  public CluesImpl getClue() {
    return clue;
  }
}
