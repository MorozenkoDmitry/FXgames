package com.example.games.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private PrimaryScene scene;

    public Main() {
    }

    public void start(Stage primaryStage) {
        try {
            this.scene = new PrimaryScene(primaryStage);
            this.scene.applicationStart();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}