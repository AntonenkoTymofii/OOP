import javax.swing.*;
import java.awt.*;

public class Rob2_2 extends JDialog{

    public Rob2_2(JFrame parentFrame) {
        super(parentFrame, "Вікно2", true);
        super.setBounds(500, 200, 350, 150);

        Container wind2 = super.getContentPane();
        wind2.setLayout(new GridLayout(1, 2, 5, 3));

        JButton back = new JButton("< Назад");
        JButton approve = new JButton("Так");

        wind2.add(back);
        wind2.add(approve);

        approve.addActionListener(e -> {
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Ви закрили вікно через ТАК");
        });

        back.addActionListener(e -> {
            setVisible(false);
            new Rob2_1(parentFrame).setVisible(true);
        });
    }
}
