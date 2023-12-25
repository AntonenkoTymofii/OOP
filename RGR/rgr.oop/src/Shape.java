import java.awt.*;

public abstract class Shape {
    protected Point startPoint;
    protected Point endPoint;
    protected Color color;
    protected static Color fillColor;
    public Shape(Point startPoint, Point endPoint, Color color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
        fillColor = null;
    }
    public abstract void draw(Graphics g);
    public void setColor(Color c) {
        this.color = c;
    }
    public static void setFillColor(Color c) {
        fillColor = c;
    }
}
