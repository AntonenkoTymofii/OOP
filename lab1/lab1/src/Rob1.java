import javax.swing.*;
import java.awt.*;

public class Rob1 extends JDialog {
    private final JScrollBar scrollBar;
    private final JLabel valueLabel;

    public Rob1(JFrame parentFrame){

        super(parentFrame, "Робота1", true);
        super.setBounds(500, 200, 350, 150);

        Container wind = super.getContentPane();
        wind.setLayout(new GridLayout(4, 1, 3, 2));

        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 1, 101);
        wind.add(scrollBar);

        valueLabel = new JLabel("Вибране значення: " + scrollBar.getValue());
        wind.add(valueLabel);

        scrollBar.addAdjustmentListener(e -> valueLabel.setText("Вибране значення: " + scrollBar.getValue()));

        JButton approve = new JButton("Так");
        JButton reject = new JButton("Відміна");

        approve.addActionListener(e -> {
            numberChoose();
            setVisible(false);
        });
        reject.addActionListener(e -> {
            scrollBar.setValue(1);
            setVisible(false);
        });

        wind.add(approve);
        wind.add(reject);
    }

    public String numberChoose() {
        return String.valueOf(scrollBar.getValue());
    }
}
