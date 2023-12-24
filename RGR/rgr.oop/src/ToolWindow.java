import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ToolWindow extends JDialog {

    private JButton[] buttons;
    public ToolWindow(JFrame parentFrame) {
        super(parentFrame, "", false);
        super.setBounds(50, 100, 50, 100);
        buttons = new JButton[6];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Button " + (i + 1));
            add(buttons[i]);
        }

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
