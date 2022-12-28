package com.example.games.gamePresets;

public class SnakeBoard extends GamePreset {
    public static final int SNAKE_BOARD_SIZE = 19;
    public static final int TINY_CEIL_SIZE = 50;
    public static final int SMALL_MARK_SIZE = 36;
    public static final String SNAKE_TITLE = "Snake";

    public SnakeBoard() {
        super(SNAKE_BOARD_SIZE, TINY_CEIL_SIZE, SMALL_MARK_SIZE, SNAKE_TITLE);
    }
}
