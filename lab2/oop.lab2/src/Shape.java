import java.awt.*;

public abstract class Shape {
    protected Point startPoint;
    protected Point endPoint;
    protected Color color;
    protected Color fillColor;
    public Shape(Point startPoint, Point endPoint, Color color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
        this.fillColor = null;
    }
    public abstract void draw(Graphics g);
    public void setColor(String c) {
        switch (c) {
            case "BLACK":
                this.color = Color.BLACK;
                break;
            case "RED":
                this.color = Color.RED;
                break;
            case "WHITE":
                this.color = Color.WHITE;
                break;
            case "GREEN":
                this.color = Color.GREEN;
                break;
        }
    }
    public void setFillColor(String c) {
        switch (c) {
            case "BLACK":
                this.fillColor = Color.BLACK;
                break;
            case "RED":
                this.fillColor = Color.RED;
                break;
            case "WHITE":
                this.fillColor = Color.WHITE;
                break;
            case "GREEN":
                this.fillColor = Color.GREEN;
                break;
        }
    }
}
