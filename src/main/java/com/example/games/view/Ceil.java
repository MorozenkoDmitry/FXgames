
package  com.example.games.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Ceil extends StackPane {
    public static final Color STANDART_TEXT_COLOR;
    private Color strokeColor;
    private int strokeSize;
    private Color fillColor;
    private Rectangle rectangle;
    private Text text = new Text("");
    private int x;
    private int y;

    static {
        STANDART_TEXT_COLOR = Color.ANTIQUEWHITE;
    }

    public Ceil(double width, double height, String startText, int x, int y, Color fillColor, Color strokeColor, int strokeSize) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeSize = strokeSize;
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(width, height);
        this.text = new Text(startText);
        this.setSeilView();
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(new Node[]{this.rectangle, this.text});
    }

    private void setSeilView() {
        this.rectangle.setFill(this.fillColor);
        this.rectangle.setStroke(this.strokeColor);
        this.rectangle.setStrokeWidth((double)this.strokeSize);
    }

    public boolean isEmpty() {
        return this.text.getText().equals("");
    }

    public String toString() {
        return this.text.getText();
    }

    public void resetText() {
        this.text.setText("");
    }

    public Text getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setText(String text, Color color, int markSize) {
        this.text.setText(text);
        this.text.setFont(Font.font((double)markSize));
        this.text.setFill(color);
    }

    public void setText(String text, Color color) {
        this.text.setText(text);
        this.text.setFill(color);
    }

    public void setText(String text, int markSize) {
        this.setText(text, STANDART_TEXT_COLOR, markSize);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
