package com.example.games.gamePresets;


import javafx.scene.paint.Color;

public class Board2048 extends GamePreset {
    public static final int GAME15_BOARD_SIZE = 4;
    public static final int LARGE_CEIL_SIZE = 100;
    public static final int BIG_MARK_SIZE = 72;
    public static final Color BOARD_CEILS_COLOR_2048 = Color.rgb(205, 193, 180, 1.0);
    public static final Color STROKE_COLOR_2048 = Color.rgb(187, 173, 160, 1.0);
    public static final int STROKE_SIZE_2048 = 12;
    public static final String GAME_2048_TITLE = "Game2048";

    public Board2048() {
        super(4, 100, 72, BOARD_CEILS_COLOR_2048, STROKE_COLOR_2048, 12, "Game2048");
    }
}