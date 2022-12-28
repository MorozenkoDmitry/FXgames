package com.example.games.games;

import com.example.games.gamePresets.Board2048;
import com.example.games.gamePresets.GamePreset;
import java.util.Arrays;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import com.example.games.view.*;

public class Game2048 implements Game {
    private Board board;
    private boolean isSomethingShifted = false;
    private int max = 0;
    private static final int POINTS_TO_WIN = 2048;
    private boolean isGameWin = false;
    private HashMap<Integer, CeilSettings> ceilColors = new HashMap();
    private static final Color BLACK_DIGIT_COLOR = Color.rgb(119, 110, 101, 1.0);
    private static final Color WHITE_DIGIT_COLOR = Color.rgb(249, 246, 242, 1.0);
    public EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.UP) {
                Game2048.this.upShist();
                Game2048.this.spawnDigitAndGameStateCheck();
            }

            if (event.getCode() == KeyCode.DOWN) {
                Game2048.this.downShist();
                Game2048.this.spawnDigitAndGameStateCheck();
            }

            if (event.getCode() == KeyCode.LEFT) {
                Game2048.this.leftShist();
                Game2048.this.spawnDigitAndGameStateCheck();
            }

            if (event.getCode() == KeyCode.RIGHT) {
                Game2048.this.rightShist();
                Game2048.this.spawnDigitAndGameStateCheck();
            }

            event.consume();
        }
    };

    public static Game getInstance(GamePreset gamePreset) {
        return new Game2048(new Board(gamePreset));
    }

    public Game2048(Board board) {
        this.board = board;
        this.newGameInitialize();
    }

    private void newGameInitialize() {
        this.ceilColorsInitialize();
        this.spawnDigit();
        this.spawnDigit();
    }

    private void ceilColorsInitialize() {
        this.ceilColors.put(2, new CeilSettings(BLACK_DIGIT_COLOR, Color.rgb(238, 228, 218, 1.0)));
        this.ceilColors.put(4, new CeilSettings(BLACK_DIGIT_COLOR, Color.rgb(237, 224, 200, 1.0)));
        this.ceilColors.put(8, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(242, 177, 121, 1.0)));
        this.ceilColors.put(16, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(245, 149, 99, 1.0)));
        this.ceilColors.put(32, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(246, 124, 95, 1.0)));
        this.ceilColors.put(64, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(246, 94, 59, 1.0)));
        this.ceilColors.put(128, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(242, 193, 65, 1.0)));
        this.ceilColors.put(256, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(242, 193, 65, 1.0)));
        this.ceilColors.put(512, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(242, 193, 65, 1.0)));
        this.ceilColors.put(1024, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(242, 193, 65, 1.0)));
        this.ceilColors.put(2048, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(242, 193, 65, 1.0)));
        this.ceilColors.put(4096, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(255, 52, 31, 1.0)));
        this.ceilColors.put(8192, new CeilSettings(WHITE_DIGIT_COLOR, Color.rgb(255, 52, 31, 1.0)));
    }

    private void spawnDigitAndGameStateCheck() {
        if (this.checkLose()) {
            this.lose();
        }

        if (this.isSomethingShifted) {
            this.spawnDigit();
        }

        if (this.checkWin()) {
            this.win();
        }

        this.isSomethingShifted = false;
    }

    private void horisontalShift(boolean isRight) {
        int boardSize = this.board.getXSize();

        for(int i = 0; i < boardSize; ++i) {
            int[] arr = new int[boardSize];

            for(int j = 0; j < boardSize; ++j) {
                int val = 0;
                if (!this.getBoardElement(i, j).isEmpty()) {
                    val = Integer.parseInt(this.getBoardElement(i, j).getText().getText());
                }

                arr[j] = val;
            }

            if (isRight) {
                arr = this.shiftLine(arr);
            } else {
                arr = this.shiftLineReverced(arr);
            }

            this.setLine(i, arr, boardSize);
        }

    }

    private void leftShist() {
        this.horisontalShift(false);
    }

    private void rightShist() {
        this.horisontalShift(true);
    }

    private void verticalShift(boolean isDown) {
        int boardSize = this.board.getXSize();

        for(int i = 0; i < boardSize; ++i) {
            int[] arr = new int[boardSize];

            for(int j = 0; j < boardSize; ++j) {
                int val = 0;
                if (!this.getBoardElement(j, i).isEmpty()) {
                    val = Integer.parseInt(this.getBoardElement(j, i).getText().getText());
                }

                arr[j] = val;
            }

            if (isDown) {
                arr = this.shiftLine(arr);
            } else {
                arr = this.shiftLineReverced(arr);
            }

            this.setColumn(i, arr, boardSize);
        }

    }

    private void setLine(int i, int[] arr, int boardSize) {
        for(int j = 0; j < boardSize; ++j) {
            this.getBoardElement(i, j).setText("", 36);
            this.getBoardElement(i, j).getRectangle().setFill(Board2048.BOARD_CEILS_COLOR_2048);
            int val = arr[j];
            CeilSettings colorSettings = (CeilSettings)this.ceilColors.get(val);
            if (val != 0) {
                this.getBoardElement(i, j).setText(String.valueOf(val), colorSettings.textColor, 36);
                this.getBoardElement(i, j).getRectangle().setFill(colorSettings.fillColor);
            }
        }

    }

    private void setColumn(int i, int[] arr, int boardSize) {
        for(int j = 0; j < boardSize; ++j) {
            this.getBoardElement(j, i).setText("", 36);
            this.getBoardElement(j, i).getRectangle().setFill(Board2048.BOARD_CEILS_COLOR_2048);
            int val = arr[j];
            CeilSettings colorSettings = (CeilSettings)this.ceilColors.get(val);
            if (val != 0) {
                this.getBoardElement(j, i).setText(String.valueOf(val), colorSettings.textColor, 36);
                this.getBoardElement(j, i).getRectangle().setFill(colorSettings.fillColor);
            }
        }

    }

    private void upShist() {
        this.verticalShift(false);
    }

    private void downShist() {
        this.verticalShift(true);
    }

    private int[] shiftLine(int[] numbers) {
        int arraySize = numbers.length;
        int[] newList = new int[arraySize];
        int index = arraySize - 1;
        int last = 0;

        for(int i = arraySize - 1; i >= 0; --i) {
            if (numbers[i] != 0) {
                if (last == numbers[i]) {
                    newList[index + 1] = 2 * numbers[i];
                    this.max = Math.max(this.max, newList[index + 1]);
                    last = 0;
                } else {
                    newList[index] = numbers[i];
                    last = numbers[i];
                    --index;
                }
            }
        }

        this.isSomethingShifted = this.isSomethingShifted || !Arrays.equals(numbers, newList);
        return newList;
    }

    private int[] shiftLineReverced(int[] numbers) {
        int arraySize = numbers.length;
        int[] newList = new int[arraySize];
        int index = 0;
        int last = 0;

        for(int i = 0; i < arraySize; ++i) {
            if (numbers[i] != 0) {
                if (last == numbers[i]) {
                    newList[index - 1] = 2 * numbers[i];
                    this.max = Math.max(this.max, newList[index - 1]);
                    last = 0;
                } else {
                    newList[index] = numbers[i];
                    last = numbers[i];
                    ++index;
                }
            }
        }

        this.isSomethingShifted = this.isSomethingShifted || !Arrays.equals(numbers, newList);
        return newList;
    }

    private boolean checkLose() {
        int boardSize = this.board.getXSize();
        boolean allCeilsAreFill = true;

        for(int i = 0; i < boardSize; ++i) {
            for(int j = 0; j < boardSize; ++j) {
                if (this.getBoardElement(i, j).isEmpty()) {
                    allCeilsAreFill = false;
                    break;
                }
            }
        }

        return allCeilsAreFill;
    }

    private boolean checkWin() {
        return this.max == 2048 && !this.isGameWin;
    }

    private void win() {
        AlertWindow winAlert = new AlertWindow("You win", "You get " + this.max + " points!", GameState.WIN, "Continue");
        winAlert.display();
        this.isGameWin = true;
    }

    private void lose() {
        AlertWindow loseAlert = new AlertWindow("You lose", "There are no space left", GameState.LOSE, "New game");
        loseAlert.display();
        this.reset();
    }

    private void reset() {
        int boardSize = this.board.getXSize();

        for(int i = 0; i < boardSize; ++i) {
            for(int j = 0; j < boardSize; ++j) {
                this.getBoardElement(i, j).setText("");
            }
        }

        this.spawnDigit();
        this.spawnDigit();
    }

    private void spawnDigit() {
        int x = this.randomNumberFrom0ToN(this.board.getXSize() - 1);
        int y = this.randomNumberFrom0ToN(this.board.getYSize() - 1);
        Ceil boardElement = this.getBoardElement(x, y);
        if (boardElement.isEmpty()) {
            int digit = this.randomDigit();
            CeilSettings colorSettings = (CeilSettings)this.ceilColors.get(digit);
            boardElement.setText(String.valueOf(digit), colorSettings.textColor, 36);
            boardElement.getRectangle().setFill(colorSettings.fillColor);
        } else {
            this.spawnDigit();
        }

    }

    private int randomDigit() {
        double randomValue = Math.random();
        return randomValue > 0.909090909 ? 4 : 2;
    }

    private int randomNumberFrom0ToN(int n) {
        int a = (int)(Math.random() * (double)(n + 1));
        return a;
    }

    private Ceil getBoardElement(int x, int y) {
        return this.board.getCeilsMatrix()[x][y];
    }

    public Board getBoard() {
        return this.board;
    }
}