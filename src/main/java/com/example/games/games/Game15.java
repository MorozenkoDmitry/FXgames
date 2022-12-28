package com.example.games.games;

import com.example.games.gamePresets.GamePreset;
import javafx.scene.paint.Color;
import com.example.games.view.*;

public class Game15 implements Game {
    private int SCRABMLE_TIMES = 2000;
    private Board board;
    private int drawSize;
    private String[][] winEtalon;
    private String whiteSpace = "";
    private Color[] colorSet;

    public static Game getInstance(GamePreset gamePreset) {
        return new Game15(new Board(gamePreset), gamePreset.getMarkSize());
    }

    public Game15(Board board, int drawSize) {
        this.colorSet = new Color[]{Color.ANTIQUEWHITE, Color.DARKKHAKI, Color.DARKORANGE, Color.DARKOLIVEGREEN, Color.LIGHTCORAL, Color.BURLYWOOD};
        this.board = board;
        this.drawSize = drawSize;
        this.makeDigitsOnBoard();
        this.setBoardOnMouseClick();
        this.winEtalon = this.makeWinEtalon();
        this.scrambleBoard(this.SCRABMLE_TIMES);
    }

    public Board getBoard() {
        return this.board;
    }

    private String[][] makeWinEtalon() {
        int size = this.board.getYSize();
        String[][] createdMatrix = new String[size][size];
        int counter = 1;

        for(int i = 0; i < createdMatrix.length; ++i) {
            for(int j = 0; j < createdMatrix.length; ++j) {
                createdMatrix[i][j] = String.valueOf(counter);
                ++counter;
            }
        }

        createdMatrix[size - 1][size - 1] = this.whiteSpace;
        return createdMatrix;
    }

    private void makeDigitsOnBoard() {
        int size = this.board.getXSize();
        int ceilTag = 1;

        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                this.getBoardElement(i, j).setText(String.valueOf(ceilTag), this.pickRandomColor(), this.drawSize);
                ++ceilTag;
            }
        }

        this.getBoardElement(size - 1, size - 1).setText(this.whiteSpace, this.drawSize);
        this.getBoardElement(size - 1, size - 1).getRectangle().setFill(Color.WHITE);
    }

    private Color pickRandomColor() {
        int colorsAmount = this.colorSet.length;
        int colorIndex = this.randomNumberFrom0ToN(colorsAmount - 1);
        return this.colorSet[colorIndex];
    }

    private void setBoardOnMouseClick() {
        for(int i = 0; i < this.board.getYSize(); ++i) {
            for(int j = 0; j < this.board.getXSize(); ++j) {
                Ceil ceil = this.getBoardElement(i, j);
                ceil.setOnMouseClicked((value) -> {
                    this.tryMakeTurn(ceil);
                });
            }
        }

    }

    private void tryMakeTurn(Ceil ceil) {
        this.trySwap(ceil);
        boolean isWin = this.winCheck();
        if (isWin) {
            this.win();
        }

    }

    private void trySwap(Ceil ceil) {
        int x = ceil.getX();
        int y = ceil.getY();
        this.goLeft(x, y);
        this.goRight(x, y);
        this.goUp(x, y);
        this.goDown(x, y);
    }

    private void goLeft(int x, int y) {
        if (x - 1 >= 0) {
            Ceil ceil = this.getBoardElement(x - 1, y);
            if (ceil.isEmpty()) {
                this.swapText(x, y, x - 1, y);
            }
        }

    }

    private void goRight(int x, int y) {
        if (x + 1 < this.board.getYSize()) {
            Ceil ceil = this.getBoardElement(x + 1, y);
            if (ceil.isEmpty()) {
                this.swapText(x, y, x + 1, y);
            }
        }

    }

    private void goUp(int x, int y) {
        if (y - 1 >= 0) {
            Ceil ceil = this.getBoardElement(x, y - 1);
            if (ceil.isEmpty()) {
                this.swapText(x, y, x, y - 1);
            }
        }

    }

    private void goDown(int x, int y) {
        if (y + 1 < this.board.getYSize()) {
            Ceil ceil = this.getBoardElement(x, y + 1);
            if (ceil.isEmpty()) {
                this.swapText(x, y, x, y + 1);
            }
        }

    }

    private void win() {
        AlertWindow winAlert = new AlertWindow("Win", "Winner winner chicken dinner", GameState.WIN, "New game");
        winAlert.display();
        this.reset();
    }

    private void scrambleBoard(int times) {
        for(int i = 0; i < times; ++i) {
            int x = this.randomNumberFrom0ToN(this.board.getYSize() - 1);
            int y = this.randomNumberFrom0ToN(this.board.getYSize() - 1);
            this.trySwap(this.getBoardElement(x, y));
        }

    }

    private int randomNumberFrom0ToN(int n) {
        int a = (int)(Math.random() * (double)(n + 1));
        return a;
    }

    private void swapText(int s1, int s2, int d1, int d2) {
        Ceil source = this.getBoardElement(s1, s2);
        Ceil destination = this.getBoardElement(d1, d2);
        String sourceText = source.toString();
        String destinationText = destination.toString();
        Color sourceTextColor = (Color)source.getText().getFill();
        Color destinationTextColor = (Color)destination.getText().getFill();
        Color sourceFillColor = (Color)source.getRectangle().getFill();
        Color destinationFillColor = (Color)destination.getRectangle().getFill();
        source.setText(destinationText, destinationTextColor);
        destination.setText(sourceText, sourceTextColor);
        source.getRectangle().setFill(destinationFillColor);
        destination.getRectangle().setFill(sourceFillColor);
    }

    private Ceil getBoardElement(int x, int y) {
        return this.board.getCeilsMatrix()[x][y];
    }

    private boolean winCheck() {
        int size = this.board.getXSize();

        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if (!this.getBoardElement(i, j).toString().equals(this.winEtalon[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }

    private void reset() {
        this.scrambleBoard(this.SCRABMLE_TIMES);
    }
}