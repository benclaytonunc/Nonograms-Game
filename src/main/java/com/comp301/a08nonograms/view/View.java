package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.model.Puzzle;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox layout = new VBox();

        // Controls view
        ControlView controlsView = new ControlView(controller);
        layout.getChildren().add(controlsView.render());

        // Playlist
        VBox playlist = new VBox();
        for (int i = 0; i < controller.getPuzzleCount(); i++) {
            PuzzleView puzzle = new PuzzleView(controller, i);
            playlist.getChildren().add(puzzle.render());
        }
        layout.getChildren().add(playlist);

        // Add song view
        // AddSongView addSongView = new AddSongView(controller);
        // layout.getChildren().add(addSongView.render());

        return layout;
    }
}