package com.example.games.games;

import javafx.scene.paint.Color;

public class Player {
    private String name;
    private char mark;
    private String winSequence = "";
    private Color markColor;

    Player(char mark, int winLenght, Color markColor, String name) {
        this.mark = mark;

        for(int i = 0; i < winLenght; ++i) {
            this.winSequence = this.winSequence + mark;
        }

        this.markColor = markColor;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return this.markColor;
    }

    public String getWinSequence() {
        return this.winSequence;
    }

    public char getMark() {
        return this.mark;
    }
}