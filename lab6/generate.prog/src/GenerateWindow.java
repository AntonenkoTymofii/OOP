import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class GenerateWindow extends JFrame {
    private int n;
    private double min;
    private double max;
    private DefaultTableModel tableModel;

    public GenerateWindow() {
        super.setTitle("Вікно генерації");
        super.setBounds(100, 350, 400, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true){
            super.setVisible(true);
            try {
                ServerSocket serverSocket = new ServerSocket(8888);
                try {
                    Socket socket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    n = dis.readInt();
                    min = dis.readDouble();
                    max = dis.readDouble();
                    socket.close();
                    dis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JTable table = new JTable();
            double[][] matrix = generateDefinedMatrix(n, min, max);
            tableModel = new DefaultTableModel(matrix.length, matrix[0].length);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    tableModel.setValueAt(matrix[i][j], i, j);
                }
            }
            table.setModel(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            super.getContentPane().add(scrollPane, BorderLayout.CENTER);
            sendData();
        }
    }

    private static double[][] generateDefinedMatrix(int n, double min, double max) {
        double[][] matrix = new double[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = min + (max - min) * random.nextDouble();
            }
        }

        while (!isMatrixDefined(matrix)) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = min + (max - min) * random.nextDouble();
                }
            }
        }

        return matrix;
    }

    private static boolean isMatrixDefined(double[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i <= n; i++) {
            double[][] minor = new double[i][i];
            for (int j = 0; j < i; j++) {
                System.arraycopy(matrix[j], 0, minor[j], 0, i);
            }

            if (calculateDeterminant(minor) <= 0) {
                return false;
            }
        }

        return true;
    }
    private static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        double determinant = 0;
        for (int col = 0; col < n; col++) {
            determinant += Math.pow(-1, col) * matrix[0][col] *
                    calculateDeterminant(minor(matrix, col));
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

    private void sendData(){
        try {
            double[][] data = readTableData(tableModel);
            try {
                Socket socket = new Socket("localhost", 9999);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(n);
                for (double[] datum : data) {
                    for (double v : datum) {
                        dos.writeDouble(v);
                    }
                }
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
    private static double[][] readTableData(DefaultTableModel tableModel) {
        int rowCount = tableModel.getRowCount();
        int colCount = tableModel.getColumnCount();
        double[][] result = new double[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Object cellValue = tableModel.getValueAt(i, j);
                if (cellValue instanceof Number) {
                    result[i][j] = ((Number) cellValue).doubleValue();
                } else {
                    result[i][j] = 0.0;
                }
            }
        }
        return result;
    }
}
