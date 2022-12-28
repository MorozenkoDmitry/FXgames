package com.example.games.games;

import com.example.games.gamePresets.GamePreset;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.paint.Color;
import com.example.games.view.*;

public class TicTacToe implements Game {
    private int turns = 0;
    private Board board;
    private Player first;
    private Player second;
    private Player activePlayer;
    private boolean isGameWin = false;
    private int drawSize;
    private int winLength;
    public static final int BIG_GAME_WIN_LENGTH = 5;
    public static final int SMALL_GAME_WIN_LENGTH = 3;

    public static Game getInstance(GamePreset gamePreset, int winLength) {
        return new TicTacToe(new Board(gamePreset), gamePreset.getMarkSize(), winLength);
    }

    public TicTacToe(Board board, int drawSize, int winLength) {
        this.board = board;
        this.setBoardOnMouseClick();
        this.first = new Player('O', winLength, Color.ORANGE, "Player 1");
        this.second = new Player('X', winLength, Color.GREENYELLOW, "Player 2");
        this.activePlayer = this.first;
        this.drawSize = drawSize;
        this.winLength = winLength;
    }

    public Board getBoard() {
        return this.board;
    }

    private void setBoardOnMouseClick() {
        for(int i = 0; i < this.board.getYSize(); ++i) {
            for(int j = 0; j < this.board.getXSize(); ++j) {
                Ceil ceil = this.board.getCeilsMatrix()[i][j];
                ceil.setOnMouseClicked((value) -> {
                    this.onCeilClickListener(ceil);
                });
            }
        }

    }

    private void onCeilClickListener(Ceil ceil) {
        if (!this.isGameWin && ceil.isEmpty()) {
            ceil.setText(String.valueOf(this.activePlayer.getMark()), this.activePlayer.getColor(), this.drawSize);
            ++this.turns;
            this.winCheck();
            this.switchTurn();
        }

        if (this.isDraw()) {
            this.draw();
        }

    }

    private boolean isDraw() {
        return this.turns == this.board.getXSize() * this.board.getYSize();
    }

    private void draw() {
        AlertWindow drawAlert = new AlertWindow("Draw", "", GameState.DRAW, "New game");
        drawAlert.display();
        this.resetGame();
    }

    private void switchTurn() {
        this.activePlayer = this.activePlayer.equals(this.first) ? this.second : this.first;
    }

    private void winCheck() {
        ArrayList<String> lines = this.collectLines();
        Iterator var3 = lines.iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            if (s.contains(this.activePlayer.getWinSequence())) {
                this.win();
            }
        }

    }

    private void win() {
        AlertWindow winAlert = new AlertWindow("Win", this.activePlayer.getName() + " (" + this.activePlayer.getMark() + ") wins", GameState.WIN, "New game");
        winAlert.display();
        this.isGameWin = true;
        this.resetGame();
    }

    public void resetGame() {
        this.turns = 0;
        this.isGameWin = false;

        for(int i = 0; i < this.board.getYSize(); ++i) {
            for(int j = 0; j < this.board.getXSize(); ++j) {
                this.board.getCeilsMatrix()[i][j].resetText();
            }
        }

    }

    private ArrayList<String> collectLines() {
        ArrayList<String> lines = new ArrayList();
        this.collectNonDiagonals(lines);
        this.collectDiagonals(lines);
        return lines;
    }

    private String getSafeCeil(int i, int j) {
        Ceil ceil = this.board.getCeilsMatrix()[i][j];
        if (ceil.isEmpty()) {
            return "#";
        } else {
            String result = ceil.toString();
            return result;
        }
    }

    private void collectNonDiagonals(ArrayList<String> lines) {
        for(int i = 0; i < this.board.getYSize(); ++i) {
            StringBuilder line = new StringBuilder();
            StringBuilder column = new StringBuilder();

            for(int j = 0; j < this.board.getXSize(); ++j) {
                line.append(this.getSafeCeil(i, j));
                column.append(this.getSafeCeil(j, i));
            }

            lines.add(line.toString());
            lines.add(column.toString());
        }

    }

    private void collectDiagonals(ArrayList<String> lines) {
        int boardSize = this.board.getYSize();

        for(int k = 0; k < boardSize; ++k) {
            StringBuilder upMain = new StringBuilder();
            StringBuilder downMain = new StringBuilder();
            StringBuilder upSecondary = new StringBuilder();
            StringBuilder downSecondary = new StringBuilder();

            for(int i = 0; i < boardSize; ++i) {
                for(int j = 0; j < boardSize; ++j) {
                    if (i + k == j) {
                        upMain.append(this.getSafeCeil(i, j));
                    } else if (i - k == j) {
                        downMain.append(this.getSafeCeil(i, j));
                    }

                    if (i + j == boardSize - 1 - k) {
                        upSecondary.append(this.getSafeCeil(i, j));
                    } else if (i + j == boardSize - 1 + k) {
                        downSecondary.append(this.getSafeCeil(i, j));
                    }
                }
            }

            lines.add(downMain.toString());
            lines.add(upMain.toString());
            lines.add(downSecondary.toString());
            lines.add(upSecondary.toString());
        }

    }
}