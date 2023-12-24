import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private final ShapeEditor shapeEditor;
    private final JRadioButton dotButton = new JRadioButton("Крапка");
    private final JRadioButton lineButton = new JRadioButton("Лінія");
    private final JRadioButton rectangleButton = new JRadioButton("Прямокутник");
    private final JRadioButton ellipseButton = new JRadioButton("Еліпс");
    private final JRadioButton cubeButton = new JRadioButton("Куб");
    private final JRadioButton lineEllipseButton = new JRadioButton("Лінія з двома кругами");
    private final MyTable table;
    private final ToolWindow toolWindow;
    private Point startPoint;
    private Point endPoint;
    private String name;

    public MainWindow() {
        ShapeEditor shapeEditor1;
        super.setTitle("Розрахункова графічна робота");
        super.setBounds(15, 15, 1250, 650);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shapeEditor1 = new ShapeEditor();
        table = MyTable.getInstance(this, shapeEditor1);
        toolWindow = new ToolWindow(this);
        JMenuItem clearDesk = new JMenuItem("Очистити");
        clearDesk.addActionListener(e -> {
            clearPanel();
        });

        JButton openTable = new JButton("Відкрити таблицю");
        openTable.addActionListener(e -> {
            table.setVisible(true);
        });

        JButton openToolWindow = new JButton("Відкрити вікно опцій");
        openTable.addActionListener(e -> {
            toolWindow.setVisible(true);
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
        optionsMenu.add(clearDesk);

        menuBar.add(optionsMenu);
        menuBar.add(openTable);
        menuBar.add(openToolWindow);
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

        //------------------------------------------------------------------

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                name = dis.readLine();
                socket.close();
                dis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        switch (name){
            case ("Dot"):
                dotButton.setSelected(true);
                break;
            case ("Line"):
                lineButton.setSelected(true);
                break;
            case "Rectangle":
                rectangleButton.setSelected(true);
                break;
            case "Ellipse":
                ellipseButton.setSelected(true);
                break;
            case "Cube":
                cubeButton.setSelected(true);
                break;
            case "Line and ellipse":
                lineEllipseButton.setSelected(true);
                break;
            case "Clear":
                clearPanel();
                break;
            case "Help":
                String message = "Програма створена для намалювання геометричних фігур.\n" +
                    "Це графічний редактор з набором таких фігур як: точка, лінія, прямокутник, еліпс.\n" +
                    "Для намалювання цих фігур ви можете використовувати Панель інстументів або Меню " +
                        "\"Малювати\".\n" + "У вас є можливість очищати поле для малювання за добопогою " +
                        "кнопки \"Очистити\".";
                JOptionPane.showMessageDialog(this, message);
                break;
        }
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
                        s.setFillColor(Color.BLUE);
                        table.addObjectTable("Прямокутник", startPoint, endPoint);
                    } else if (ellipseButton.isSelected()) {
                        s.setFillColor(null);
                        table.addObjectTable("Еліпс", startPoint, endPoint);
                    } else if (cubeButton.isSelected()) {
                        s.setFillColor(null);
                        table.addObjectTable("Куб", startPoint, endPoint);
                    } else if (lineEllipseButton.isSelected()) {
                        s.setFillColor(null);
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