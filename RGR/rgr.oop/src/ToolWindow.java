import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ToolWindow extends JDialog {

    private final JButton[] buttons = new JButton[8];
    public ToolWindow(JFrame parentFrame) {
        super(parentFrame, "", false);
        super.setBounds(50, 100, 50, 100);

        ImageIcon dot = new ImageIcon("dot.png");
        ImageIcon line = new ImageIcon("line.png");
        ImageIcon rectangle = new ImageIcon("rectangle.png");
        ImageIcon ellipse = new ImageIcon("ellipse.png");
        ImageIcon cube = new ImageIcon("cube.png");
        ImageIcon lineEllipse = new ImageIcon("lineEllipse.png");
        ImageIcon trash = new ImageIcon("trash.png");
        ImageIcon help = new ImageIcon("help.png");


        buttons[0] = new JButton("", dot);
        buttons[1] = new JButton("", line);
        buttons[2] = new JButton("", rectangle);
        buttons[3] = new JButton("", ellipse);
        buttons[4] = new JButton("", cube);
        buttons[5] = new JButton("", lineEllipse);
        buttons[6] = new JButton("", trash);
        buttons[7] = new JButton("", help);


        buttons[0].setToolTipText("Точка");
        buttons[1].setToolTipText("Лінія");
        buttons[2].setToolTipText("Прямокутник");
        buttons[3].setToolTipText("Еліпс");
        buttons[4].setToolTipText("Куб");
        buttons[5].setToolTipText("Лінія з еліпсом");
        buttons[6].setToolTipText("Очистити");
        buttons[7].setToolTipText("Довідка");

        buttons[0].addActionListener(e -> {
            sendData("Dot");
        });
        buttons[1].addActionListener(e -> {
            sendData("Line");
        });
        buttons[2].addActionListener(e -> {
            sendData("Rectangle");
        });
        buttons[3].addActionListener(e -> {
            sendData("Ellipse");
        });
        buttons[4].addActionListener(e -> {
            sendData("Cube");
        });
        buttons[5].addActionListener(e -> {
            sendData("Line and ellipse");
        });
        buttons[6].addActionListener(e -> {
            sendData("Clear");
        });
        buttons[7].addActionListener(e -> {
            sendData("Help");
        });

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
    private void sendData(String message){
        try {

            try {
                Socket socket = new Socket("localhost", 8888);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeChars(message);
                socket.close();
                dos.close();

            } catch (IOException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка відправки даних на сервер!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Введіть коректні числові значення!");
        }
    }
}
