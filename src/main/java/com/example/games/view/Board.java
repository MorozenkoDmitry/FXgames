package com.example.games.view;

import com.example.games.gamePresets.GamePreset;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Board {
    public static final int MENU_OFFSET = 30;
    private Group ceilsGroup = new Group();
    private Ceil[][] ceilsMatrix;
    private int xSize;
    private int ySize;
    private double ceilSize;
    private Color ceilFillColor;
    private Color strokeColor;
    private int strokeSize;

    public Board(GamePreset gamePreset) {
        this.xSize = gamePreset.getBoardSize();
        this.ySize = gamePreset.getBoardSize();
        this.ceilSize = (double)gamePreset.getCeilSize();
        this.ceilFillColor = gamePreset.getCeilFillColor();
        this.strokeColor = gamePreset.getStrokeColor();
        this.strokeSize = gamePreset.getStrokeSize();
        this.makeBoardMatrix();
    }

    private Board(int xSize, int ySize, double ceilSize, Color ceilFillColor, Color strokeColor, int strokeSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.ceilSize = ceilSize;
        this.ceilFillColor = ceilFillColor;
        this.strokeColor = strokeColor;
        this.ceilSize = (double)strokeSize;
        this.makeBoardMatrix();
    }

    private void makeBoardMatrix() {
        this.ceilsMatrix = new Ceil[this.xSize][this.ySize];

        for(int i = 0; i < this.ySize; ++i) {
            for(int j = 0; j < this.xSize; ++j) {
                Ceil element = new Ceil(this.ceilSize, this.ceilSize, "", i, j, this.ceilFillColor, this.strokeColor, this.strokeSize);
                element.setTranslateX((double)j * this.ceilSize);
                element.setTranslateY((double)i * this.ceilSize + 30.0);
                this.ceilsGroup.getChildren().add(element);
                this.ceilsMatrix[i][j] = element;
            }
        }

    }

    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    public double getCeilSize() {
        return this.ceilSize;
    }

    public Ceil[][] getCeilsMatrix() {
        return this.ceilsMatrix;
    }

    public Group getCeilsGroup() {
        return this.ceilsGroup;
    }

    public double getBoardXSize() {
        return (double)this.xSize * this.ceilSize;
    }

    public double getBoardYSize() {
        return (double)this.ySize * this.ceilSize;
    }
}
