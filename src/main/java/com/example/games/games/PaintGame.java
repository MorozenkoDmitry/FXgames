package com.example.games.games;

import com.example.games.gamePresets.GamePreset;
import com.example.games.gamePresets.PaintBoard;
import com.example.games.view.AlertWindow;
import com.example.games.view.Board;
import com.example.games.view.Ceil;
import javafx.scene.paint.Color;

public class PaintGame implements Game {

    private int winCounter = 0;
    private Board board;
    private boolean isGameWin = false;


    private Color startColor = Color.rgb(238, 228, 218, 1.0);
    private Color winColor = Color.rgb(25, 215, 53, 1.0);
    public static Game getInstance(GamePreset gamePreset) {
        return new PaintGame(new Board(gamePreset));
    }

    public PaintGame(Board board) {
        this.board = board;
        this.setBoardOnMouseClick();
    }

    public Board getBoard() {
        return this.board;
    }

    private void setBoardOnMouseClick() {
        for(int i = 0; i < this.board.getYSize(); ++i) {
            for(int j = 0; j < this.board.getXSize(); ++j) {
                Ceil ceil = this.board.getCeilsMatrix()[i][j];
                ceil.getRectangle().setFill(startColor);
                ceil.setOnMouseClicked((value) -> {
                    this.onCeilClickListener(ceil);
                });
            }
        }

    }

    private void onCeilClickListener(Ceil ceil) {
        reverseFill(ceil);
        right(ceil);
        left(ceil);
        down(ceil);
        up(ceil);

        if (winCounter == PaintBoard.PAINT_BOARD_SIZE * PaintBoard.PAINT_BOARD_SIZE) {
            this.isGameWin = true;
            win();
        }

    }

    private void reverseFill(Ceil ceil) {
        if(ceil.getRectangle().getFill().equals(startColor)) {
            ceil.getRectangle().setFill(winColor);
            winCounter++;
        } else if(ceil.getRectangle().getFill().equals(winColor)) {
                ceil.getRectangle().setFill(startColor);
                winCounter--;
            }
    }

    private void left(Ceil ceil) {
        if (ceil.getY() != 0) {
            Ceil leftCeil = this.board.getCeilsMatrix()[ceil.getX()][ceil.getY() - 1];
            reverseFill(leftCeil);
        }
    }

    private void right(Ceil ceil) {
        if (ceil.getY() != PaintBoard.PAINT_BOARD_SIZE - 1) {
            Ceil rightCeil = this.board.getCeilsMatrix()[ceil.getX()][ceil.getY() + 1];
            reverseFill(rightCeil);
        }
    }

    private void down(Ceil ceil) {
        if (ceil.getX() != PaintBoard.PAINT_BOARD_SIZE - 1) {
            Ceil downCeil = this.board.getCeilsMatrix()[ceil.getX() + 1][ceil.getY()];
            reverseFill(downCeil);
        }
    }

    private void up(Ceil ceil) {
        if (ceil.getX() != 0) {
            Ceil upCeil = this.board.getCeilsMatrix()[ceil.getX() - 1][ceil.getY()];
            reverseFill(upCeil);
        }
    }


    private void reset() {
        for(int i = 0; i < this.board.getYSize(); ++i) {
            for(int j = 0; j < this.board.getXSize(); ++j) {
                Ceil ceil = this.board.getCeilsMatrix()[i][j];
                ceil.getRectangle().setFill(startColor);
                isGameWin = false;
                winCounter = 0;
            }
        }
    }
    private void win() {
        AlertWindow winAlert = new AlertWindow("Win", "Winner winner chicken dinner", GameState.WIN, "New game");
        winAlert.display();
        this.reset();
    }
}
