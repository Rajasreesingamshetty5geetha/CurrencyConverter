package currency;
import javax.swing.*;
import java.awt.*;


public class Currencyans {
    private static final String[] currencies = {"Rupee (₹)", "Dollar ($)", "Euro (€)", "Yen (¥)"};
    private static final double[][] conversionRates = {
        {1, 0.012, 0.011, 1.79},  // Rupee
        {79.37, 1, 0.98, 147.95}, // Dollar
        {80.85, 1.02, 1, 160.37}, // Euro
        {0.59, 0.0068, 0.0062, 1} // Yen
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Currency Converter");
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(6, 1, 5, 5));
            frame.getContentPane().setBackground(new Color(50, 50, 50));

            JLabel title = new JLabel("Currency Converter", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 16));
            title.setForeground(Color.WHITE);

            JPanel inputPanel = new JPanel(new FlowLayout());
            inputPanel.setBackground(new Color(50, 50, 50));
            JLabel amountLabel = new JLabel("Input Amount:");
            amountLabel.setForeground(Color.WHITE);
            JTextField amountField = new JTextField(10);
            inputPanel.add(amountLabel);
            inputPanel.add(amountField);

            JPanel fromPanel = new JPanel(new FlowLayout());
            fromPanel.setBackground(new Color(50, 50, 50));
            JLabel fromLabel = new JLabel("From:");
            fromLabel.setForeground(Color.WHITE);
            JComboBox<String> fromCurrency = new JComboBox<>(currencies);
            fromPanel.add(fromLabel);
            fromPanel.add(fromCurrency);

            JPanel toPanel = new JPanel(new FlowLayout());
            toPanel.setBackground(new Color(50, 50, 50));
            JLabel toLabel = new JLabel("To:");
            toLabel.setForeground(Color.WHITE);
            JComboBox<String> toCurrency = new JComboBox<>(currencies);
            toPanel.add(toLabel);
            toPanel.add(toCurrency);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBackground(new Color(50, 50, 50));
            JButton convertButton = new JButton("Convert");
            JButton clearButton = new JButton("Clear");
            clearButton.setBackground(Color.RED);
            clearButton.setForeground(Color.WHITE);
            buttonPanel.add(convertButton);
            buttonPanel.add(clearButton);

            JTextField resultField = new JTextField();
            resultField.setEditable(false);
            resultField.setHorizontalAlignment(JTextField.CENTER);

            convertButton.addActionListener(e -> {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    int fromIndex = fromCurrency.getSelectedIndex();
                    int toIndex = toCurrency.getSelectedIndex();
                    double convertedAmount = amount * conversionRates[fromIndex][toIndex];
                    resultField.setText(String.format("%.2f %s", convertedAmount, currencies[toIndex]));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            clearButton.addActionListener(e -> {
                amountField.setText("");
                resultField.setText("");
            });

            frame.add(title);
            frame.add(inputPanel);
            frame.add(fromPanel);
            frame.add(toPanel);
            frame.add(buttonPanel);
            frame.add(resultField);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
