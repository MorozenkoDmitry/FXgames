package com.example.games.view;

import com.example.games.games.GameState;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {
    public static final Image WIN_IMAGE = new Image("/winner.png");
    public static final Image DRAW_IMAGE = new Image("/draw.jpg");
    public static final Image LOSE_IMAGE = new Image("/loseImage.jpg");
    public static final int WINDOW_SIZE = 250;
    private String closeButtonText;
    private String title;
    private String message;
    private GameState windowType;
    private Stage window;

    public AlertWindow(String title, String message, GameState windowType, String closeButtonText) {
        this.title = title;
        this.message = message;
        this.windowType = windowType;
        this.closeButtonText = closeButtonText;
        this.window = new Stage();
    }

    public void display() {
        this.window.initModality(Modality.APPLICATION_MODAL);
        this.window.setTitle(this.title);
        this.window.setMinWidth(250.0);
        Scene scene = new Scene(this.makeLayout());
        this.window.getIcons().add(PrimaryScene.APP_ICON);
        this.window.setScene(scene);
        this.window.showAndWait();
    }

    private VBox makeLayout() {
        Label text = new Label();
        text.setText(this.message);
        Button closeButton = new Button(this.closeButtonText);
        closeButton.setOnAction((event) -> {
            this.window.close();
        });
        VBox layout = new VBox(10.0);
        layout.getChildren().addAll(new Node[]{text, this.selectSuitableImage(), closeButton});
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private ImageView selectSuitableImage() {
        Image gameResult;
        if (this.windowType == GameState.DRAW) {
            gameResult = DRAW_IMAGE;
        } else if (this.windowType == GameState.WIN) {
            gameResult = WIN_IMAGE;
        } else {
            gameResult = LOSE_IMAGE;
        }

        ImageView picture = new ImageView();
        picture.setImage(gameResult);
        return picture;
    }
}
