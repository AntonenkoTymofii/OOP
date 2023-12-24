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

    public MainWindow() {
        super.setTitle("Лабораторна №3");
        super.setBounds(15, 15, 1250, 650);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shapeEditor = new ShapeEditor();
        JMenuItem clearDesk = new JMenuItem("Очистити");
        clearDesk.addActionListener(e -> {clearPanel();});

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
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (rectangleButton.isSelected()) {
                    super.setTitle("Режим малювання прямокутників");
                } else if (ellipseButton.isSelected()) {
                    super.setTitle("Режим малювання еліпсів");
                } else if (lineButton.isSelected()) {
                    super.setTitle("Режим малювання ліній");
                } else if (dotButton.isSelected()) {
                    super.setTitle("Режим малювання точок");
                }
            }
        };
        rectangleButton.addItemListener(itemListener);
        ellipseButton.addItemListener(itemListener);
        lineButton.addItemListener(itemListener);
        dotButton.addItemListener(itemListener);

        //------------------------------------------------------------------

        JToolBar toolbar = new JToolBar();
        Container cont = super.getContentPane();

        ImageIcon dot = new ImageIcon("dot.png");
        ImageIcon line = new ImageIcon("line.png");
        ImageIcon rectangle = new ImageIcon("rectangle.png");
        ImageIcon ellipse = new ImageIcon("ellipse.png");
        ImageIcon trash = new ImageIcon("trash.png");
        ImageIcon help = new ImageIcon("help.png");


        JButton dotToolButton = new JButton("", dot);
        JButton lineToolButton = new JButton("", line);
        JButton rectangleToolButton = new JButton("", rectangle);
        JButton ellipseToolButton = new JButton("", ellipse);
        JButton trashToolButton = new JButton("", trash);
        JButton helpToolButton = new JButton("", help);

        dotToolButton.setToolTipText("Точка");
        lineToolButton.setToolTipText("Лінія");
        rectangleToolButton.setToolTipText("Прямокутник");
        ellipseToolButton.setToolTipText("Еліпс");
        trashToolButton.setToolTipText("Очистити");
        helpToolButton.setToolTipText("Довідка");

        dotToolButton.addActionListener(e -> {
            dotButton.setSelected(true);
        });

        lineToolButton.addActionListener(e -> {
            lineButton.setSelected(true);
        });

        rectangleToolButton.addActionListener(e -> {
            rectangleButton.setSelected(true);

        });

        ellipseToolButton.addActionListener(e -> {
            ellipseButton.setSelected(true);
        });

        trashToolButton.addActionListener(e -> {clearPanel();});

        helpToolButton.addActionListener(e -> {
            String message = "Програма створена для намалювання геометричних фігур.\n" +
                    "Це графічний редактор з набором таких фігур як: точка, лінія, прямокутник, еліпс.\n" +
                    "Для намалювання цих фігур ви можете використовувати Панель інстументів або Меню \"Малювати\".\n" +
                    "У вас є можливість очищати поле для малювання за добопогою кнопки \"Очистити\".";
            JOptionPane.showMessageDialog(this, message);
        });

        toolbar.add(dotToolButton);
        toolbar.add(lineToolButton);
        toolbar.add(rectangleToolButton);
        toolbar.add(ellipseToolButton);
        toolbar.add(trashToolButton);
        toolbar.add(helpToolButton);

        cont.add(toolbar, BorderLayout.NORTH);
        super.add(toolbar);
        getContentPane().add(panel);
        panel.add(toolbar ,BorderLayout.CENTER);
    }

    private void clearPanel() {
        String message = "Було очищено " + shapeEditor.getSize() + " фігур";
        shapeEditor.clearShapes();
        repaint();
        JOptionPane.showMessageDialog(this, message);
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
                    newShape = new Rectangle(startPoint, startPoint, Color.BLUE);
                } else if (dotButton.isSelected()) {
                    newShape = new Dot(startPoint, startPoint, Color.BLUE);
                } else if (lineButton.isSelected()) {
                    newShape = new Line(startPoint, startPoint, Color.BLUE);
                } else if (ellipseButton.isSelected()) {
                    newShape = new Ellipse(startPoint, startPoint, Color.BLUE);
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
                    s.endPoint = e.getPoint();
                    s.setColor(Color.BLACK);
                    if (rectangleButton.isSelected()) {
                        s.setFillColor(Color.BLUE);
                    } else if (ellipseButton.isSelected()) {
                        s.setFillColor(null);
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

