import java.util.ArrayList;

public class ShapeEditor {
    private final ArrayList<Shape> shapeArr = new ArrayList<>();
    public void addShape(Shape shape) {
        shapeArr.add(shape);
    }
    public ArrayList<Shape> getShapes() {
        return shapeArr;
    }
    public void clearShapes() {
        shapeArr.clear();
    }
    public int getSize() {
        return shapeArr.size();
    }
    public void deleteShape(int selected){
        shapeArr.remove(selected);
    }
}
