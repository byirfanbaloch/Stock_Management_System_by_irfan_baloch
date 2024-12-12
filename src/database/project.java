package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class project extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField itemNameField;
    private JTextField quantityField;
    private JTextArea displayArea;
    private ArrayList<Item> StockList;

    public project() {
        setTitle("Stock Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        StockList = new ArrayList<>();

        createUI();
    }

    private void createUI() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(60, 63, 65));

        JLabel titleLabel = new JLabel("Stock Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(new Color(80, 83, 85));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Item Name:");
        nameLabel.setForeground(Color.WHITE);
        itemNameField = new JTextField(20);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(Color.WHITE);
        quantityField = new JTextField(20);

        JButton addButton = new JButton("Add Item");
        styleButton(addButton);

        JButton removeButton = new JButton("Remove Item");
        styleButton(removeButton);

        JButton displayButton = new JButton("Display Stock");
        styleButton(displayButton);

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton);

        inputPanel.add(nameLabel);
        inputPanel.add(itemNameField);
        inputPanel.add(Box.createVerticalStrut(10));

        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(Box.createVerticalStrut(10));

        inputPanel.add(addButton);
        inputPanel.add(Box.createVerticalStrut(10));

        inputPanel.add(removeButton);
        inputPanel.add(Box.createVerticalStrut(10));

        inputPanel.add(displayButton);
        inputPanel.add(Box.createVerticalStrut(10));

        inputPanel.add(exitButton);  // Added exit button

        contentPanel.add(inputPanel, BorderLayout.WEST);

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Stock List"));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addItem());
        removeButton.addActionListener(e -> removeItem());
        displayButton.addActionListener(e -> displayStock());
        exitButton.addActionListener(e -> System.exit(0));  // Action listener for exit button

        add(contentPanel);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setPreferredSize(new Dimension(150, 30));
    }

    // Method to add item
    private void addItem() {
        String itemName = itemNameField.getText();
        String quantityText = quantityField.getText();

        if (itemName.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both item name and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            StockList.add(new Item(itemName, quantity));
            JOptionPane.showMessageDialog(this, "Item added to Stock.");
            itemNameField.setText("");
            quantityField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to remove item
    private void removeItem() {
        String itemName = itemNameField.getText();

        if (itemName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the item name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean found = false;
        for (Item item : StockList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                StockList.remove(item);
                found = true;
                JOptionPane.showMessageDialog(this, "Item removed from Stock.");
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Item not found in the Stock.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        itemNameField.setText("");
        quantityField.setText("");
    }

    // Method to display Stock
    private void displayStock() {
        StringBuilder StockDisplay = new StringBuilder();
        for (Item item : StockList) {
            StockDisplay.append("Item: ").append(item.getName())
                    .append("\n, Quantity: ").append(item.getQuantity()).append("\n");
        }

        if (StockList.isEmpty()) {
            StockDisplay.append("Stock is empty.");
        }

        displayArea.setText(StockDisplay.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            project StockSystem = new project();
            StockSystem.setVisible(true);
        });
    }
}

class Item {
    private String name;
    private int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}