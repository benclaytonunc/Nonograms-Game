package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.awt.event.MouseEvent;

public class ControlView implements FXComponent {
  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();
    layout.setPadding(new Insets(10, 10, 10, 10));
//changes

  //end
    Button changeButton = new Button("Change Puzzle");
    changeButton.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });
    layout.getChildren().add(changeButton);

    return layout;
  }
}
