package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.controller.ControllerImpl;
import com.comp301.a08nonograms.model.BoardImpl;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import java.awt.*;

public class SquareMaker implements FXComponent {
  private final Controller controller;

  public SquareMaker(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    // BoardImpl s = new BoardImpl(height, width);

    for (int i = 0; i < controller.getClues().getHeight(); i++) {
      for (int k = 0; k < controller.getClues().getWidth(); k++) {
        Button butt = new Button();
        int c = i;
        int d = k;
        if (controller.isShaded(c, d)) {
          butt.setStyle("-fx-background-color: green");
        } else if (controller.isEliminated(c, d)) {
          butt.setStyle("-fx-background-color: #dc143c");
        }
        butt.setOnMousePressed(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                controller.toggleShaded(c, d);
              } else if (event.getButton() == MouseButton.SECONDARY) {
                controller.toggleEliminated(c, d);
              }
            });
        grid.add(butt, c, d);
      }
    }


    return grid;
  }
}
