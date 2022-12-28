package com.example.games.gamePresets;

import javafx.scene.paint.Color;

public class GamePreset {
    public static final Color STANDART_CEIL_FILL_COLOR;
    public static final Color STANDART_STROKE_COLOR;
    public static final int STANDART_STROKE_SIZE = 3;
    private int boardSize;
    private int ceilSize;
    private int markSize;
    private Color ceilFillColor;
    private Color strokeColor;
    private int strokeSize;
    private String title;

    static {
        STANDART_CEIL_FILL_COLOR = Color.CADETBLUE;
        STANDART_STROKE_COLOR = Color.WHITE;
    }

    public GamePreset(int boardSize, int ceilSize, int markSize, String title) {
        this.boardSize = boardSize;
        this.ceilSize = ceilSize;
        this.markSize = markSize;
        this.title = title;
        this.ceilFillColor = STANDART_CEIL_FILL_COLOR;
        this.strokeColor = STANDART_STROKE_COLOR;
        this.strokeSize = 3;
    }

    public GamePreset(int boardSize, int ceilSize, int markSize, Color ceilFillColor, Color strokeColor, int strokeSize, String title) {
        this.boardSize = boardSize;
        this.ceilSize = ceilSize;
        this.markSize = markSize;
        this.ceilFillColor = ceilFillColor;
        this.strokeColor = strokeColor;
        this.strokeSize = strokeSize;
        this.title = title;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public int getCeilSize() {
        return this.ceilSize;
    }

    public int getMarkSize() {
        return this.markSize;
    }

    public Color getCeilFillColor() {
        return this.ceilFillColor;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    public int getStrokeSize() {
        return this.strokeSize;
    }

    public String getTitle() {
        return this.title;
    }
}
