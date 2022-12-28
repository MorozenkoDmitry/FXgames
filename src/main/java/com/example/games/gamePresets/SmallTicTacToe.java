package com.example.games.gamePresets;

public class SmallTicTacToe extends GamePreset {
    public static final int SMALL_BOARD_SIZE = 3;
    public static final int LARGE_CEIL_SIZE = 100;
    public static final int BIG_MARK_SIZE = 72;
    public static final String SMALL_TIC_TAC_TOE_TITLE = "3x3 tic tac toe";

    public SmallTicTacToe() {
        super(3, 100, 72, "3x3 tic tac toe");
    }
}
