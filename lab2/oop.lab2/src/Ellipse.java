import java.awt.*;

public class Ellipse extends Shape{

    public Ellipse(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }
    @Override
    public void draw(Graphics g) {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        if(fillColor != null) {
            g.setColor(fillColor);
            g.fillOval(x, y, width, height);
        }
        g.setColor(color);
        g.drawOval(x, y, width, height);
    }
}
