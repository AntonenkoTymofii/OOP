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
    public void setColor(Color c) {
        this.color = c;
    }
    public void setFillColor(Color c) {
        this.fillColor = c;
    }
}
