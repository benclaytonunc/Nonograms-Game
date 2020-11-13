package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.PuzzleLibrary;
import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.model.BoardImpl;
import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.CluesImpl;
import com.comp301.a08nonograms.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PuzzleView {
  private final Controller controller;
  private final int Index;
  private List<Clues> clues;

  public PuzzleView(Controller controller, int Index) { //, List<Clues> clues) {
  //  this.clues = clues;
    this.controller = controller;
    this.Index = Index;
  }

  public Parent render() {
    HBox layout = new HBox();
    layout.setAlignment(Pos.CENTER_LEFT);
    layout.setSpacing(5);
    layout.setStyle("-fx-border-color: #888888");
    layout.setPadding(new Insets(5, 5, 5, 5));
    clues = PuzzleLibrary.create();
    Puzzle newPuzz = new Puzzle(clues.get(Index), Index);
    // BoardImpl newPuzzBoard = newPuzz.getBoard();
   // SquareMaker sqM = new SquareMaker(controller, clues.get(Index).getColCluesLength(), clues.get(Index).getRowCluesLength());
    // layout.getChildren().add(sqM.render());
   // controller.nextPuzzle();
    Button clear = new Button("\u239A");
    clear.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });
    layout.getChildren().add(clear);
    Button downB = new Button("\u25BC");
    downB.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        //  newPuzz.setPuzzleIndex(Index - 1);
        });
    layout.getChildren().add(downB);

    Button upB = new Button("\u25B2");
    upB.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        //  newPuzz.setPuzzleIndex(Index + 1);
          // new PuzzleView(controller, Index + 1).render();
        });
    layout.getChildren().add(upB);

    // Title
    Label title = new Label(makeLabelString());
    layout.getChildren().add(title);

    return layout;
  }

  private String makeLabelString() {
    return "Puzzle # " + (controller.getPuzzleIndex() + 1);
  }
}
