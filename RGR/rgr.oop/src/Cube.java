import java.awt.*;

public class Cube extends Shape{
    private final Line[] lines = new Line[4];
    private final Rectangle[] rectangles = new Rectangle[2];
    public Cube(Point startPoint, Point endPoint, Color color){
        super(startPoint, endPoint, color);
    }
    @Override
    public void draw(Graphics g) {
        int x1 = Math.min(startPoint.x, endPoint.x);
        int y1 = Math.min(startPoint.y, endPoint.y);

        int x2 = Math.min(startPoint.x, endPoint.x) + 30;
        int y2 = Math.min(startPoint.y, endPoint.y) + 30;

        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);

        rectangles[0] = new Rectangle(startPoint, endPoint, color);
        rectangles[1] = new Rectangle(new Point(startPoint.x + 30, startPoint.y + 30),
                new Point(endPoint.x + 30, endPoint.y + 30), color);

        lines[0] = new Line(new Point(x1, y1), new Point(x2, y2), color);
        lines[1] = new Line(new Point(x1 + width, y1), new Point(x2 + width, y2), color);
        lines[2] = new Line(new Point(x1, y1 + height), new Point(x2, y2 + height), color);
        lines[3] = new Line(new Point(x1 + width, y1 + height), new Point(x2 + width, y2 + height), color);
        g.setColor(color);

        lines[1].draw(g);
        lines[2].draw(g);
        lines[3].draw(g);

        for (Rectangle rectangle : rectangles){
            rectangle.draw(g);
        }
        lines[0].draw(g);
    }
}
