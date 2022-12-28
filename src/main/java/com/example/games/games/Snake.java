package com.example.games.games;

import com.example.games.gamePresets.GamePreset;
import com.example.games.gamePresets.SnakeBoard;
import com.example.games.view.Board;


import com.example.games.view.AlertWindow;
import com.example.games.view.Ceil;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake implements Game {

    private Board board;

    private int points = 0;

    private ArrayList<Position> positions = new ArrayList<>();

    private int xPosition = 2;
    private int yPosition = 2;
    private int oldX;
    private int oldY;

    private String fromWhere = "";

    private boolean gameEnd = false;

    private Color playerColor = Color.rgb(11, 50, 232, 1.0);

    private Color appleColor = Color.rgb(115, 48, 70, 1.0);

    private Color mineColor = Color.rgb(12, 11, 3, 1.0);
    public static Game getInstance(GamePreset gamePreset) {
        return new Snake(new Board(gamePreset));
    }

    public EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.UP) {
                if(!fromWhere.equals("DOWN") || points == 0) {
                    fromWhere = "UP";
                    up();
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if(!fromWhere.equals("UP") || points == 0) {
                    fromWhere = "DOWN";
                    down();
                }
            }

            if (event.getCode() == KeyCode.LEFT) {
                if(!fromWhere.equals("RIGHT") || points == 0) {
                    fromWhere = "LEFT";
                    left();
                }
            }

            if (event.getCode() == KeyCode.RIGHT) {
                if(!fromWhere.equals("LEFT") || points == 0) {
                    fromWhere = "RIGHT";
                    right();
                }
            }

            event.consume();
        }
    };

    private void left() {
        if (xPosition != 0) {
            saveOldIndexs();
            xPosition--;
            positions.add(new Position(xPosition, yPosition));
            clearOldPosition();
            paintNewPosition();

        }
    }
    private void right() {
        if (xPosition < SnakeBoard.SNAKE_BOARD_SIZE - 1) {
            saveOldIndexs();
            xPosition++;
            positions.add(new Position(xPosition, yPosition));
            clearOldPosition();
            paintNewPosition();

        }
    }
    private void up() {
        if (yPosition != 0) {
            saveOldIndexs();
            yPosition--;
            positions.add(new Position(xPosition, yPosition));
            clearOldPosition();
            paintNewPosition();

        }
    }
    private void down() {
        if (yPosition < SnakeBoard.SNAKE_BOARD_SIZE - 1) {
            saveOldIndexs();
            yPosition++;
            positions.add(new Position(xPosition, yPosition));
            clearOldPosition();
            paintNewPosition();

        }
    }

    private void paintNewPosition() {
        Ceil newPosition = this.board.getCeilsMatrix()[yPosition][xPosition];
        if (newPosition.getRectangle().getFill().equals(appleColor)) {
            points++;
            positions.add(0, new Position(oldX, oldY));
            collectedSpawn();
        }
        if (newPosition.getRectangle().getFill().equals(mineColor) ||
                newPosition.getRectangle().getFill().equals(playerColor)) {
            AlertWindow loseAlert = new AlertWindow("You lose", "Boom!", GameState.LOSE, "New game");
            loseAlert.display();
            reset();
        } else {
            paintSnake();
            if (points == 10) {
                win();
            }
        }
    }

    private void saveOldIndexs() {
        oldX = positions.get(0).x;
        oldY = positions.get(0).y;
    }
    private void clearOldPosition() {
        positions.remove(0);
        Ceil oldPosition = this.board.getCeilsMatrix()[oldY][oldX];
        oldPosition.getRectangle().setFill(GamePreset.STANDART_CEIL_FILL_COLOR);
    }
    public Snake(Board board) {
        this.board = board;
        newGame();
    }

    public void newGame() {

        positions = new ArrayList<>();
        makeAppleOrMine(3, appleColor);
        makeAppleOrMine(3, mineColor);
        positions.add(new Position(xPosition, yPosition));
        paintSnake();
        collectedSpawn();
    }

    private void paintSnake() {
        for (int i = 0; i < positions.size(); i++) {
            Ceil playerCeil = this.board.getCeilsMatrix()[positions.get(i).y][positions.get(i).x];
            playerCeil.getRectangle().setFill(playerColor);
        }
    }

    public void collectedSpawn() {
        makeAppleOrMine(3, appleColor);
        makeAppleOrMine(3, mineColor);
    }
    public void makeAppleOrMine(int gameObject, Color color) {
        for (int i = 0; i < gameObject; i++) {
            int x = (int) (Math.random() * SnakeBoard.SNAKE_BOARD_SIZE - 1);
            int y = (int) (Math.random() * SnakeBoard.SNAKE_BOARD_SIZE - 1);
            if (x == xPosition && y == yPosition ) {
                makeAppleOrMine(gameObject, color);
            } else {
                Ceil appleCeil = this.board.getCeilsMatrix()[x][y];
                appleCeil.getRectangle().setFill(color);
            }
        }
    }

    private void reset() {
        gameEnd = true;
        points = 0;
        for(int i = 0; i < this.board.getYSize(); ++i) {
            for(int j = 0; j < this.board.getXSize(); ++j) {
                Ceil ceil = this.board.getCeilsMatrix()[i][j];
                ceil.getRectangle().setFill(GamePreset.STANDART_CEIL_FILL_COLOR);
            }
        }
        newGame();
    }
    public Board getBoard() {
        return this.board;
    }

    private void win() {
        AlertWindow winAlert = new AlertWindow("Win", "Winner winner chicken dinner", GameState.WIN, "New game");
        winAlert.display();
        reset();
    }
}

