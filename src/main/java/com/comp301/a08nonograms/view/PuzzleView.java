package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PuzzleView {
  private final Controller controller;
  private final int Index;

  public PuzzleView(Controller controller, int Index) {
    this.controller = controller;
    this.Index = Index;
  }

  public Parent render() {
    HBox layout = new HBox();
    layout.setAlignment(Pos.CENTER_LEFT);
    layout.setSpacing(5);
    layout.setPadding(new Insets(5, 5, 5, 5));
    layout.setStyle("-fx-border-color: #888888");

    Button downB = new Button("\u25BC");
    downB.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        });
    layout.getChildren().add(downB);

    Button upB = new Button("\u25B2");
    upB.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });
    layout.getChildren().add(upB);

    Button deleteB = new Button("\u274C");
    deleteB.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
        });
    layout.getChildren().add(deleteB);

    // Title
    Label title = new Label(makeLabelString());
    layout.getChildren().add(title);

    return layout;
  }

  private String makeLabelString() {
    return "Puzzle # " + controller.getPuzzleIndex();
  }
}
