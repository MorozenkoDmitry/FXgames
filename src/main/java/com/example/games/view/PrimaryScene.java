package com.example.games.view;

import com.example.games.gamePresets.*;
import com.example.games.games.*;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PrimaryScene {
    private Game currentGame;
    private GamePreset gamePreset;
    public static final Image APP_ICON = new Image("/icon.png");
    //private Music music;
    private BorderPane root;
    private Stage stage;

    public PrimaryScene(Stage stage) {
        this.stage = stage;
    }

    public void applicationStart() {
        //this.music = new Music();
        //this.music.musicMuteSwitch();
        this.startSnakeGame();
    }

    private void setScene() {
        this.root = new BorderPane();
        Board board = this.currentGame.getBoard();
        Scene scene = new Scene(this.root, board.getBoardXSize() + (double)this.gamePreset.getStrokeSize(), board.getBoardYSize() + (double)this.gamePreset.getStrokeSize() + 30.0, Color.LIGHTYELLOW);
        this.root.getChildren().addAll(new Node[]{board.getCeilsGroup()});
        this.addMenuBar();
        this.stage.getIcons().add(APP_ICON);
        this.stage.setScene(scene);
        this.stage.setTitle(this.gamePreset.getTitle());
        this.stage.show();
    }

    private void addMenuBar() {
        Menu menu = new Menu("_Settings");
        menu.getItems().addAll(this.setMenuItems());
        MenuBar topMenuBar = new MenuBar();
        topMenuBar.getMenus().add(menu);
        this.root.setTop(topMenuBar);
    }

    private List<MenuItem> setMenuItems() {
        MenuItem exit = new MenuItem("_Exit");
        MenuItem smallTicTacToeGame = new MenuItem("New tic tac toe game(3x3)");
        MenuItem bigTicTacToeGame = new MenuItem("New tic tac toe game(big)");
        MenuItem game2048 = new MenuItem("New game 2048");
        MenuItem game15 = new MenuItem("New game 15");
        MenuItem paintGame = new MenuItem("New paint Game");
        MenuItem snake = new MenuItem("Snake");
        //MenuItem mute = new MenuItem("_Music on\\off");
        exit.setOnAction((e) -> {
            System.exit(0);
        });
        //mute.setOnAction((e) -> {
          //  this.music.musicMuteSwitch();
        //});
        smallTicTacToeGame.setOnAction((e) -> {
            this.startSmallTicTacToe();
        });
        bigTicTacToeGame.setOnAction((e) -> {
            this.startBigTicTacToe();
        });
        game15.setOnAction((e) -> {
            this.startGame15();
        });
        game2048.setOnAction((e) -> {
            this.startGame2048();
        });
        paintGame.setOnAction((e) -> {
            this.startPaintGame();
        });
        snake.setOnAction((e) -> {
            this.startSnakeGame();
        });
        List<MenuItem> items = Arrays.asList(exit, smallTicTacToeGame, bigTicTacToeGame, game15, game2048, paintGame, snake);
        return items;
    }

    private void startSmallTicTacToe() {
        this.gamePreset = new SmallTicTacToe();
        this.currentGame = TicTacToe.getInstance(this.gamePreset, 3);
        this.setScene();
    }

    private void startBigTicTacToe() {
        this.gamePreset = new BigTicTacToe();
        this.currentGame = TicTacToe.getInstance(this.gamePreset, 5);
        this.setScene();
    }

    private void startGame15() {
        this.gamePreset = new Game15Board();
        this.currentGame = Game15.getInstance(this.gamePreset);
        this.setScene();
    }

    private void startGame2048() {
        this.gamePreset = new Board2048();
        this.currentGame = Game2048.getInstance(this.gamePreset);
        this.setScene();
        Game2048 convertedGame = (Game2048)this.currentGame;
        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, convertedGame.keyListener);
    }

    private void startPaintGame() {
        this.gamePreset = new PaintBoard();
        this.currentGame = PaintGame.getInstance(this.gamePreset);
        this.setScene();
    }

    private void startSnakeGame() {
        this.gamePreset = new SnakeBoard();
        this.currentGame = Snake.getInstance(this.gamePreset);
        this.setScene();
        Snake convertedGame = (Snake)this.currentGame;
        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, convertedGame.keyListener);
    }
}