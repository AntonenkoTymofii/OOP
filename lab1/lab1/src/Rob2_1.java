import javax.swing.*;
import java.awt.*;

public class Rob2_1 extends JDialog{

    public Rob2_1(JFrame parentFrame) {
        super(parentFrame, "Вікно1", true);
        super.setBounds(500, 200, 350, 150);

        Container wind1 = super.getContentPane();
        wind1.setLayout(new GridLayout(1, 2, 5, 3));

        JButton reject = new JButton("Відміна");
        JButton next = new JButton("Далі >");

        wind1.add(reject);
        wind1.add(next);

        reject.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Ви закрили вікно через Відміна");
            setVisible(false);
        });

        next.addActionListener(e -> {
            setVisible(false);
            new Rob2_2(parentFrame).setVisible(true);
        });
    }
}
