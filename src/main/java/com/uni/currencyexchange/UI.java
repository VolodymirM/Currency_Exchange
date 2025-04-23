package com.uni.currencyexchange;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame {

    private static JLabel resultLabel;

    private Consumer<String> fromDropdownCallback;
    private Consumer<String> toDropdownCallback;
    private Consumer<String> amountCallback;
    private Runnable buttonCallback;

    public UI() {
        setTitle("Currency Exchange");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] currencies = {"USD", "EUR", "GBP", "CHF", "PLN", "ILS", "UAH",  "RUB"};

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Result label
        gbc.gridx = 1;
        gbc.gridy = 4;
        resultLabel = new JLabel("Converted amount: --");
        panel.add(resultLabel, gbc);

        // From Currency dropdown
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("From:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> fromCurrency = new JComboBox<>(currencies);
        panel.add(fromCurrency, gbc);
        fromCurrency.addActionListener(e -> {
            resultLabel.setText("Converted amount: --");
            String selectedCurrency = (String) fromCurrency.getSelectedItem();
            if (selectedCurrency != null) {
                fromDropdownCallback.accept(selectedCurrency);
            }
        });

        // To Currency dropdown
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("To:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> toCurrency = new JComboBox<>(currencies);
        panel.add(toCurrency, gbc);
        toCurrency.addActionListener(e -> {
            resultLabel.setText("Converted amount: --");
            String selectedCurrency = (String) toCurrency.getSelectedItem();
            if (selectedCurrency != null) {
                toDropdownCallback.accept(selectedCurrency);
            }
        });

        // Amount field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        JTextField amountField = new JTextField();
        panel.add(amountField, gbc);
        amountField.addActionListener(e -> {
            resultLabel.setText("Converted amount: --");
            String amountStr = amountField.getText();
            if (amountStr != null && !amountStr.isEmpty()) {
                amountCallback.accept(amountStr);
            }
        });
        amountField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String amountStr = amountField.getText();
                if (amountStr != null && !amountStr.isEmpty()) {
                    amountCallback.accept(amountStr);
                }
            }
        });

        // Convert button
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(e -> {
            if (buttonCallback != null) {
                buttonCallback.run();
            }
        });
        panel.add(convertButton, gbc);

        setResizable(false);
        setLocationRelativeTo(null);
        add(panel);
        setVisible(true);
    }
    
    public void changeResult(Double result) {
        if (result != null) {
            resultLabel.setText("Converted amount: " + result);
            revalidate();
            repaint();
        }
        else {
            resultLabel.setText("Converted amount: --");
            revalidate();
            repaint();
        }
    }
    
    public void setFromDropdown(Consumer<String> callback) {this.fromDropdownCallback = callback;}
    public void setToDropdown(Consumer<String> callback) {this.toDropdownCallback = callback;}
    public void buttonPressed(Runnable callback) {this.buttonCallback = callback;}
    public void changeAmount(Consumer<String> callback) {this.amountCallback = callback;}
    

}
