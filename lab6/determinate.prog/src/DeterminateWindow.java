import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DeterminateWindow extends JFrame {
    private double[][] matrix;
    public DeterminateWindow(){
        super.setTitle("Вікно розрахунку детермінанта");
        super.setBounds(600, 350, 400, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel determinate = new JLabel();
        super.add(determinate);
        while (true){
            super.setVisible(true);
            try {
                ServerSocket serverSocket = new ServerSocket(9999);
                try {
                    Socket socket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    int n = dis.readInt();
                    matrix = new double[n][n];
                    for (int i = 0; i < n; i++){
                        for (int j = 0; j < n; j++){
                            matrix[i][j] = dis.readDouble();
                        }
                    }
                    socket.close();
                    dis.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            determinate.setText("Значення детермінанта: " +
                    determinantRecursive(matrix));
        }
    }

    private static double determinantRecursive(double[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        double determinant = 0;

        for (int col = 0; col < n; col++) {
            determinant += Math.pow(-1, col) * matrix[0][col] * determinantRecursive(minor(matrix, col));
        }

        return determinant;
    }

    private static double[][] minor(double[][] matrix, int colToRemove) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];

        int newRow = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                continue;
            }
            int newCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == colToRemove) {
                    continue;
                }
                minor[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }
        return minor;
    }
}
