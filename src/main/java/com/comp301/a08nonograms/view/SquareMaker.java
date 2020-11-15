package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.controller.ControllerImpl;
import com.comp301.a08nonograms.model.BoardImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.awt.*;

public class SquareMaker implements FXComponent {
  private final Controller controller;
  private final int height;
  private final int width;

  public SquareMaker(Controller controller, int height, int width) {
    this.controller = controller;
    this.height = height;
    this.width = width;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    // BoardImpl s = new BoardImpl(height, width);

    for (int i = 0; i < height; i++) {
      for (int k = 0; k < width; k++) {
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
    if (controller.isSolved()) {
      Text text = new Text();
      text.setText("You Won! good job");
      text.setX(50);
      text.setY(50);
      grid.add(text, 50, 50);
    }

    return grid;
  }
}
