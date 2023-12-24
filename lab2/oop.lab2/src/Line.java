import java.awt.*;

public class Line extends Shape{

    public Line(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
