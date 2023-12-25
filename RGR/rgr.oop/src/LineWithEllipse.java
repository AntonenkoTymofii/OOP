import java.awt.*;

public class LineWithEllipse extends Shape{

    public LineWithEllipse(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }

    @Override
    public void draw(Graphics g) {
        Line line = new Line(startPoint, endPoint, color);
        line.draw(g);

        Ellipse ellipse1 = new Ellipse(startPoint, new Point(startPoint.x + 20,
                startPoint.y + 20), color);
        Ellipse ellipse2 = new Ellipse(endPoint, new Point(endPoint.x + 20,
                endPoint.y + 20), color);


        g.setColor(color);
        ellipse1.draw(g);
        ellipse2.draw(g);
    }
}
