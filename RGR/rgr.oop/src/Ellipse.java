import java.awt.*;

public class Ellipse extends Shape{

    public Ellipse(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }
    @Override
    public void draw(Graphics g) {
        int width = Math.abs(endPoint.x - startPoint.x) * 2;
        int height = Math.abs(endPoint.y - startPoint.y) * 2;
        int x = startPoint.x - (width / 2);
        int y = startPoint.y - (height / 2);
        if(fillColor != null) {
            g.setColor(fillColor);
            g.fillOval(x, y, width, height);
        }
        g.setColor(color);
        g.drawOval(x, y, width, height);
    }
}
