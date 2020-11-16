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
    layout.setAlignment(Pos.CENTER_LEFT);
    layout.setSpacing(20);
    layout.setStyle("-fx-border-color: #888888");
    layout.setPadding(new Insets(100, 50, 30, 30));
    VBox rowcolgrid = new VBox();
    HBox cluesAndGrid = new HBox();

    HBox RowClues = new HBox();
    RowClues.setAlignment(Pos.TOP_RIGHT);
    RowClues.setSpacing(9.5);
    for (int i = 0; i < controller.getClues().getHeight(); i++) {
      VBox singleRow = new VBox();
      for (int j = 0; j < controller.getClues().getRowCluesLength(); j++) {
        Label help = new Label("" + controller.getClues().getRowClues(i)[j]);
        singleRow.getChildren().add(help);
        if (j < controller.getClues().getRowCluesLength() - 1) {
          Label comma = new javafx.scene.control.Label(",");
          singleRow.getChildren().add(comma);
        }
      }
      RowClues.getChildren().add(singleRow);
    }
    rowcolgrid.getChildren().add(RowClues);

    VBox ColClues = new VBox();
    ColClues.setSpacing(8.2);
    for (int i = 0; i < controller.getClues().getWidth(); i++) {
      HBox singleCol = new HBox();
      for (int j = 0; j < controller.getClues().getColCluesLength(); j++) {
        Label help = new Label("" + controller.getClues().getColClues(i)[j]);
        singleCol.getChildren().add(help);
        if (j < controller.getClues().getColCluesLength() - 1) {
          Label comma = new javafx.scene.control.Label(",");
          singleCol.getChildren().add(comma);
        }
      }
      ColClues.getChildren().add(singleCol);
    }
    cluesAndGrid.getChildren().add(ColClues);

    SquareMaker sqMaker = new SquareMaker(controller);
    // layout.getChildren().add(sqMaker.render());
    cluesAndGrid.getChildren().add(sqMaker.render());
    rowcolgrid.getChildren().add(cluesAndGrid);
    layout.getChildren().add(rowcolgrid);
    PuzzleView puzzle = new PuzzleView(controller, 0);
    puzzleList.getChildren().add(puzzle.render());
    layout.getChildren().add(puzzleList);

    return layout;
  }
}
