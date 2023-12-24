import java.awt.*;

public class LineWithEllipse extends Shape{
    private Line line;
    private final Ellipse[] ellipses = new Ellipse[2];

    public LineWithEllipse(Point startPoint, Point endPoint, Color color) {
        super(startPoint, endPoint, color);
    }

    @Override
    public void draw(Graphics g) {
        line = new Line (startPoint, endPoint, color);
        line.draw(g);

        ellipses[0] = new Ellipse(startPoint, new Point(startPoint.x + 20,
                startPoint.y + 20), color);
        ellipses[1] = new Ellipse(endPoint, new Point(endPoint.x + 20,
                endPoint.y + 20), color);

        ellipses[0].setFillColor(Color.WHITE);
        ellipses[1].setFillColor(Color.WHITE);

        g.setColor(color);

        for (Ellipse ellipse : ellipses){
            ellipse.draw(g);
        }

    }
}
