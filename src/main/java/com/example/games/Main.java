package com.example.games;

import com.example.games.view.PrimaryScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    private PrimaryScene scene;
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            this.scene = new PrimaryScene(primaryStage);
            this.scene.applicationStart();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}