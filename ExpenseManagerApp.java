import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ExpenseManagerApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/expense_db";
    static final String USER = "root";
    static final String PASS = "Ekta@1204";

    private JFrame frame;
    private JTextField amountField, categoryField, dateField, descField;
    private JTextArea outputArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseManagerApp().createGUI());
    }

    private void createGUI() {
        frame = new JFrame("Expense Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        panel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        panel.add(categoryField);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        panel.add(dateField);

        panel.add(new JLabel("Description:"));
        descField = new JTextField();
        panel.add(descField);

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(e -> addExpense());
        panel.add(addButton);

        JButton viewButton = new JButton("View Expenses");
        viewButton.addActionListener(e -> viewExpenses());
        panel.add(viewButton);

        frame.add(panel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addExpense() {
        String amount = amountField.getText();
        String category = categoryField.getText();
        String date = dateField.getText();
        String desc = descField.getText();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "INSERT INTO expenses (amount, category, date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, Double.parseDouble(amount));
            stmt.setString(2, category);
            stmt.setString(3, date);
            stmt.setString(4, desc);
            stmt.executeUpdate();
            outputArea.setText("Expense added successfully!\n");
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    private void viewExpenses() {
        outputArea.setText("");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");

            while (rs.next()) {
                outputArea.append("ID: " + rs.getInt("id") +
                        ", Amount: " + rs.getDouble("amount") +
                        ", Category: " + rs.getString("category") +
                        ", Date: " + rs.getString("date") +
                        ", Description: " + rs.getString("description") + "\n");
            }
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }
}
