import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainWindow extends JFrame {
    private final JTextField num;
    private final JTextField min;
    private final JTextField max;
    public MainWindow() {
        super.setTitle("Лабораторна №6");
        super.setBounds(400, 20, 550, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container wind = super.getContentPane();
        wind.setLayout(new GridLayout(4, 2, 3, 2));

        JLabel numberLabel = new JLabel("Введіть розмірність матриці: ");
        num = new JTextField();

        JLabel minLabel = new JLabel("Введіть мінімальний коефіцієнт матриці: ");
        min = new JTextField();

        JLabel maxLabel = new JLabel("Введіть максимальний коефіцієнт матриці: ");
        max = new JTextField();

        JButton send = new JButton("Надіслати");

        send.addActionListener(e->{
            sendData();
        });

        wind.add(numberLabel);
        wind.add(num);
        wind.add(minLabel);
        wind.add(min);
        wind.add(maxLabel);
        wind.add(max);
        wind.add(send);


    }
    private void sendData(){
        try {
            int num_m = Integer.parseInt(num.getText());
            double min_m = Double.parseDouble(min.getText());
            double max_m = Double.parseDouble(max.getText());

            try {
                Socket socket = new Socket("localhost", 8888);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(num_m);
                dos.writeDouble(min_m);
                dos.writeDouble(max_m);
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
