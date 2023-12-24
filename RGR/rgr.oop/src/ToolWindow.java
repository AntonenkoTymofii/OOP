import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class ToolWindow extends JDialog {

    private final JButton[] buttons = new JButton[7];
    public ToolWindow(JFrame parentFrame) {
        super(parentFrame, "", false);
        super.setBounds(50, 100, 50, 400);

        ImageIcon dot = new ImageIcon("dot.png");
        ImageIcon line = new ImageIcon("line.png");
        ImageIcon rectangle = new ImageIcon("rectangle.png");
        ImageIcon ellipse = new ImageIcon("ellipse.png");
        ImageIcon cube = new ImageIcon("cube.png");
        ImageIcon lineEllipse = new ImageIcon("lineEllipse.png");
        ImageIcon help = new ImageIcon("help.png");


        buttons[0] = new JButton("", dot);
        buttons[1] = new JButton("", line);
        buttons[2] = new JButton("", rectangle);
        buttons[3] = new JButton("", ellipse);
        buttons[4] = new JButton("", cube);
        buttons[5] = new JButton("", lineEllipse);
        buttons[6] = new JButton("", help);


        buttons[0].setToolTipText("Точка");
        buttons[1].setToolTipText("Лінія");
        buttons[2].setToolTipText("Прямокутник");
        buttons[3].setToolTipText("Еліпс");
        buttons[4].setToolTipText("Куб");
        buttons[5].setToolTipText("Лінія з еліпсом");
        buttons[6].setToolTipText("Довідка");

        buttons[0].addActionListener(e -> {
            MainWindow.dotButton.setSelected(true);
        });
        buttons[1].addActionListener(e -> {
            MainWindow.lineButton.setSelected(true);
        });
        buttons[2].addActionListener(e -> {
            MainWindow.rectangleButton.setSelected(true);
        });
        buttons[3].addActionListener(e -> {
            MainWindow.ellipseButton.setSelected(true);
        });
        buttons[4].addActionListener(e -> {
            MainWindow.cubeButton.setSelected(true);
        });
        buttons[5].addActionListener(e -> {
            MainWindow.lineEllipseButton.setSelected(true);
        });
        buttons[6].addActionListener(e -> {
            String message = "Програма створена для намалювання геометричних фігур.\n" +
                    "Це графічний редактор з набором таких фігур як: точка, лінія, прямокутник, еліпс.\n" +
                    "Для намалювання цих фігур ви можете використовувати Панель інстументів або Меню \"Малювати\".\n" +
                    "У вас є можливість очищати поле для малювання за добопогою кнопки \"Очистити\".";
            JOptionPane.showMessageDialog(this, message);
        });

        super.add(buttons[0]);
        super.add(buttons[1]);
        super.add(buttons[2]);
        super.add(buttons[3]);
        super.add(buttons[4]);
        super.add(buttons[5]);
        super.add(buttons[6]);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustButtonLayout();
            }
        });
    }
    private void adjustButtonLayout() {
        int width = getWidth();
        int height = getHeight();

        if (width >= height) {
            int buttonWidth = width / 9;
            int buttonHeight = height;
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setBounds(i * buttonWidth, 0, buttonWidth, buttonHeight);
            }
        } else {
            int buttonWidth = width;
            int buttonHeight = height / 9;
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setBounds(0, i * buttonHeight, buttonWidth, buttonHeight);
            }
        }
    }
}
