import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private final ShapeEditor shapeEditor;
    private final JRadioButton dotButton = new JRadioButton("Крапка");
    private final JRadioButton lineButton = new JRadioButton("Лінія");
    private final JRadioButton rectangleButton = new JRadioButton("Прямокутник");
    private final JRadioButton ellipseButton = new JRadioButton("Еліпс");

    public MainWindow(){
        super.setTitle("Лабораторна №2");
        super.setBounds(250, 50, 800, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shapeEditor = new ShapeEditor();
        JMenuItem clearDesk = new JMenuItem("Clear");
        clearDesk.addActionListener(e -> {
            String message = "Було очищено " + shapeEditor.getSize() + " фігур";
            shapeEditor.clearShapes();
            repaint();
            JOptionPane.showMessageDialog(this, message);
        });

        JPanel panel = getJPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Малювати");

        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(dotButton);
        buttonGroup.add(lineButton);
        buttonGroup.add(rectangleButton);
        buttonGroup.add(ellipseButton);

        optionsMenu.add(dotButton);
        optionsMenu.add(lineButton);
        optionsMenu.add(rectangleButton);
        optionsMenu.add(ellipseButton);
        optionsMenu.add(clearDesk);

        menuBar.add(optionsMenu);
        super.setJMenuBar(menuBar);

        ItemListener itemListener = e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                if(rectangleButton.isSelected()){
                    super.setTitle("Режим малювання прямокутників");
                } else if(ellipseButton.isSelected()) {
                    super.setTitle("Режим малювання еліпсів");
                } else if(lineButton.isSelected()) {
                    super.setTitle("Режим малювання ліній");
                } else if(dotButton.isSelected()){
                    super.setTitle("Режим малювання точок");
                }
            }
        };

        rectangleButton.addItemListener(itemListener);
        ellipseButton.addItemListener(itemListener);
        lineButton.addItemListener(itemListener);
        dotButton.addItemListener(itemListener);

        getContentPane().add(panel);
    }

    private JPanel getJPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graph) {
                super.paintComponent(graph);
                for (Shape shape : shapeEditor.getShapes()) {
                    shape.draw(graph);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point startPoint = e.getPoint();
                Shape newShape = null;
                if (rectangleButton.isSelected()) {
                    newShape = new Rectangle(startPoint, startPoint, Color.RED);
                } else if (dotButton.isSelected()) {
                    newShape = new Dot(startPoint, startPoint, Color.RED);
                } else if (lineButton.isSelected()) {
                    newShape = new Line(startPoint, startPoint, Color.RED);
                } else if (ellipseButton.isSelected()) {
                    newShape = new Ellipse(startPoint, startPoint, Color.RED);
                }
                if (newShape != null) {
                    newShape.setColor("RED");
                    shapeEditor.addShape(newShape);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ArrayList<Shape> shapes = shapeEditor.getShapes();
                if (!shapes.isEmpty()) {
                    Shape s = shapes.get((shapes.size() - 1));
                    s.endPoint = e.getPoint();
                    s.setColor("BLACK");
                    if (rectangleButton.isSelected()) {
                        s.setFillColor("GREEN");
                    } else if (ellipseButton.isSelected()) {
                        s.setFillColor("WHITE");
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

