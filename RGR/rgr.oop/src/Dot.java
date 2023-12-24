import java.awt.*;

public class Dot extends Shape{
    public Dot(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }
    @Override
    public void draw(Graphics g) {
        int x = startPoint.x - 3;
        int y = startPoint.y - 3;
        int dotWidth = 4;
        int dotHeight = 4;
        g.fillOval(x, y, dotWidth, dotHeight);
    }
}

