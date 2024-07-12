

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainApp {
    private static Map<String, Estudiante> estudiantes = new HashMap<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Estudiantes");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        JButton insertButton = new JButton("INGRESE AL ESTUDIANTE");
        JButton searchButton = new JButton("BUSCAR POR CEDULA");

        insertButton.setFont(new Font("Arial", Font.BOLD, 14));
        insertButton.setBackground(Color.BLACK);
        insertButton.setForeground(Color.WHITE);

        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);

        mainPanel.setBackground(Color.YELLOW);

        mainPanel.add(insertButton);
        mainPanel.add(searchButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame insertFrame = new JFrame("Insertar Estudiante");
                insertFrame.setSize(400, 200);
                insertFrame.add(new InsertForm());
                insertFrame.setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame searchFrame = new JFrame("Buscar Estudiante");
                searchFrame.setSize(400, 200);
                searchFrame.add(new SearchForm());
                searchFrame.setVisible(true);
            }
        });
    }

    static class InsertForm extends JPanel {
        public InsertForm() {
            setLayout(new GridLayout(3, 2));
            setBackground(Color.WHITE);

            JLabel nombreLabel = new JLabel("Nombre:");
            JTextField nombreText = new JTextField(20);

            JLabel cedulaLabel = new JLabel("Cédula:");
            JTextField cedulaText = new JTextField(20);

            JButton insertButton = new JButton("Insertar");

            add(nombreLabel);
            add(nombreText);
            add(cedulaLabel);
            add(cedulaText);
            add(insertButton);

            insertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = nombreText.getText();
                    String cedula = cedulaText.getText();
                    Estudiante estudiante = new Estudiante(nombre, cedula);
                    estudiantes.put(cedula, estudiante);
                    JOptionPane.showMessageDialog(null, "Estudiante ingresado: " + estudiante);
                }
            });
        }
    }

    static class SearchForm extends JPanel {
        public SearchForm() {
            setLayout(new GridLayout(3, 2));
            setBackground(Color.WHITE);

            JLabel cedulaLabel = new JLabel("Cédula:");
            JTextField cedulaText = new JTextField(20);

            JButton searchButton = new JButton("Buscar");
            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);

            add(cedulaLabel);
            add(cedulaText);
            add(searchButton);
            add(resultArea);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cedula = cedulaText.getText();
                    Estudiante estudiante = estudiantes.get(cedula);
                    if (estudiante != null) {
                        resultArea.setText("Estudiante encontrado: " + estudiante);
                    } else {
                        resultArea.setText("Estudiante no encontrado.");
                    }
                }
            });
        }
    }
}
