import javax.swing.*;
import java.awt.*;

public class FormProgram extends JFrame {
    private final JLabel outText;
    private final Rob1 rob1wind;
    private final Rob2_1 rob21Wind;

    public FormProgram() {
        super("Лабораторна №1");
        super.setBounds(500, 200, 350, 150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rob1wind = new Rob1(this);
        rob21Wind = new Rob2_1(this);

        Container wind = super.getContentPane();
        wind.setLayout(new GridLayout(1, 1, 3, 2));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Роботи");
        JMenuItem rob1 = new JMenuItem("Робота 1");
        JMenuItem rob2 = new JMenuItem("Робота 2");

        menu.add(rob1);
        menu.add(rob2);
        menuBar.add(menu);
        super.setJMenuBar(menuBar);

        outText = new JLabel("Обране число:");
        wind.add(outText);

        rob1.addActionListener(e -> {
            rob1wind.setVisible(true);
            setOutText(rob1wind.numberChoose());
        });

        rob2.addActionListener(e -> {
            rob21Wind.setVisible(true);
        });
    }

    public void setOutText(String num){
        outText.setText("Обране число: " + num);
    }
}
