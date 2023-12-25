
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private final ShapeEditor shapeEditor;
    public static JRadioButton dotButton = new JRadioButton("Крапка");
    public static JRadioButton lineButton = new JRadioButton("Лінія");
    public static JRadioButton rectangleButton = new JRadioButton("Прямокутник");
    public static JRadioButton ellipseButton = new JRadioButton("Еліпс");
    public static JRadioButton cubeButton = new JRadioButton("Куб");
    public static JRadioButton lineEllipseButton = new JRadioButton("Лінія з двома кругами");
    private final MyTable table;
    private final ToolWindow toolWindow;
    private Point startPoint;
    private Point endPoint;


    public MainWindow() {
        ShapeEditor shapeEditor1;
        super.setTitle("Розрахункова графічна робота");
        super.setBounds(15, 15, 1250, 650);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.repaint();

        shapeEditor1 = new ShapeEditor();
        table = MyTable.getInstance(this, shapeEditor1);
        toolWindow = new ToolWindow(this);

        JButton clearDesk = new JButton("Очистити");
        clearDesk.addActionListener(e -> clearPanel());

        JButton openTable = new JButton("Відкрити таблицю");
        openTable.addActionListener(e -> {
            table.setVisible(true);
            super.repaint();
        });

        JButton openToolWindow = new JButton("Відкрити вікно опцій");
        openToolWindow.addActionListener(e -> {
            toolWindow.setVisible(true);
            super.repaint();
        });

        shapeEditor1 = table.getShapeEditor();
        shapeEditor = shapeEditor1;
        super.repaint();
        JPanel panel = getJPanel();
        super.repaint();
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Малювати");



        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(dotButton);
        buttonGroup.add(lineButton);
        buttonGroup.add(rectangleButton);
        buttonGroup.add(ellipseButton);
        buttonGroup.add(cubeButton);
        buttonGroup.add(lineEllipseButton);

        optionsMenu.add(dotButton);
        optionsMenu.add(lineButton);
        optionsMenu.add(rectangleButton);
        optionsMenu.add(ellipseButton);
        optionsMenu.add(cubeButton);
        optionsMenu.add(lineEllipseButton);


        menuBar.add(optionsMenu);
        menuBar.add(openTable);
        menuBar.add(openToolWindow);
        menuBar.add(clearDesk);
        super.setJMenuBar(menuBar);
        super.repaint();

        ItemListener itemListener = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (rectangleButton.isSelected()) {
                    super.setTitle("Режим малювання прямокутників");
                } else if (ellipseButton.isSelected()) {
                    super.setTitle("Режим малювання еліпсів");
                } else if (lineButton.isSelected()) {
                    super.setTitle("Режим малювання ліній");
                } else if (dotButton.isSelected()) {
                    super.setTitle("Режим малювання точок");
                } else if (cubeButton.isSelected()){
                    super.setTitle("Режим малювання куба");
                } else if (lineEllipseButton.isSelected()){
                    super.setTitle("Режим малювання лінії з кругами");
                }
            }
        };
        rectangleButton.addItemListener(itemListener);
        ellipseButton.addItemListener(itemListener);
        lineButton.addItemListener(itemListener);
        dotButton.addItemListener(itemListener);
        cubeButton.addItemListener(itemListener);
        lineEllipseButton.addItemListener(itemListener);

        super.add(panel);
        super.repaint();

        //------------------------------------------------------------------


    }

    private void clearPanel() {
        String message = "Було очищено " + shapeEditor.getSize() + " фігур";
        shapeEditor.clearShapes();
        repaint();
        JOptionPane.showMessageDialog(this, message);
        table.clearTable();
    }

    private JPanel getJPanel() {
        super.repaint();
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graph) {
                super.paintComponent(graph);
                for (Shape shape : shapeEditor.getShapes()) {
                    shape.draw(graph);
                    repaint();
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                Shape newShape = null;
                if (rectangleButton.isSelected()) {
                    newShape = new Rectangle(startPoint, startPoint, Color.BLUE);
                } else if (dotButton.isSelected()) {
                    newShape = new Dot(startPoint, startPoint, Color.BLUE);
                } else if (lineButton.isSelected()) {
                    newShape = new Line(startPoint, startPoint, Color.BLUE);
                } else if (ellipseButton.isSelected()) {
                    newShape = new Ellipse(startPoint, startPoint, Color.BLUE);
                } else if (cubeButton.isSelected()) {
                    newShape = new Cube(startPoint, startPoint, Color.BLUE);
                } else if (lineEllipseButton.isSelected()) {
                    newShape = new LineWithEllipse(startPoint, startPoint, Color.BLUE);
                }
                if (newShape != null) {
                    newShape.setColor(Color.BLUE);
                    shapeEditor.addShape(newShape);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ArrayList<Shape> shapes = shapeEditor.getShapes();
                if (!shapes.isEmpty()) {
                    Shape s = shapes.get((shapes.size() - 1));
                    endPoint = e.getPoint();
                    s.endPoint = endPoint;
                    s.setColor(Color.BLACK);
                    if (rectangleButton.isSelected()) {
                        table.addObjectTable("Прямокутник", startPoint, endPoint);
                    } else if (ellipseButton.isSelected()) {
                        table.addObjectTable("Еліпс", startPoint, endPoint);
                    } else if (cubeButton.isSelected()) {
                        table.addObjectTable("Куб", startPoint, endPoint);
                    } else if (lineEllipseButton.isSelected()) {
                        table.addObjectTable("Лінія з кругами", startPoint, endPoint);
                    } else if (lineButton.isSelected()) {
                        table.addObjectTable("Лінія", startPoint, endPoint);
                    } else if (dotButton.isSelected()) {
                        table.addObjectTable("Точка", startPoint, endPoint);
                    }
                    repaint();
                }
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                ArrayList<Shape> shapes = shapeEditor.getShapes();
                if (!shapes.isEmpty()) {
                    shapes.get(shapes.size() - 1).endPoint = e.getPoint();
                    repaint();
                }
            }
        });
        return panel;
    }
}