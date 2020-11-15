package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.PuzzleLibrary;
import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.CluesImpl;
import com.comp301.a08nonograms.model.Puzzle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class View implements FXComponent {
  private final Controller controller;
  private PuzzleView puzzle;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    HBox puzzleList = new HBox();
    List<Clues> clues = PuzzleLibrary.create();
    layout.setAlignment(Pos.CENTER_LEFT);
    layout.setSpacing(20);
    layout.setStyle("-fx-border-color: #888888");
    layout.setPadding(new Insets(30, 30, 30, 30));

    SquareMaker sqMaker =
        new SquareMaker(
            controller,
            controller.getClues().getRowCluesLength(),
            controller.getClues().getColCluesLength());
    layout.getChildren().add(sqMaker.render());
    PuzzleView puzzle = new PuzzleView(controller, 0);
    puzzleList.getChildren().add(puzzle.render());
    /*  for (int i = 0; i < clues.get(0).getRowCluesLength(); i++) {
          String coordinate = clues.get(0).getRowClues(i);
          layout.getChildren().addAll(new Label("Name:"));
        }
    */
    // }
    layout.getChildren().add(puzzleList);

    return layout;
  }
}
