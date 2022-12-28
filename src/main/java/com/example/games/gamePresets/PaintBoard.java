package com.example.games.gamePresets;

public class PaintBoard extends GamePreset {
    public static final int PAINT_BOARD_SIZE = 4;
    public static final int LARGE_CEIL_SIZE = 100;
    public static final String PAINT_TITLE = "Paint game";

    public PaintBoard() {
        super(PAINT_BOARD_SIZE, LARGE_CEIL_SIZE, 72, PAINT_TITLE);
    }
}