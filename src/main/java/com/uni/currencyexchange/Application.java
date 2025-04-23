package com.uni.currencyexchange;

public class Application {
    private static UI ui;
    private static String fromCurrency = "";
    private static String toCurrency = "";
    private Double amount = null;

    Application() {
        ui = new UI();

        ui.setFromDropdown((String currency) -> {
            fromCurrency = currency;
            System.out.println("From Currency: " + fromCurrency);
        });
        
        ui.setToDropdown((String currency) -> {
            toCurrency = currency;
            System.out.println("To Currency: " + toCurrency);
        });
        
        ui.changeAmount((String amountStr) -> {
            try {
                this.amount = null;
                this.amount = Double.valueOf(amountStr);
                System.out.println("Amount: " + this.amount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered: " + amount);
            }
        });
        
        ui.buttonPressed(() -> {
            ui.changeResult(amount);
            System.out.println("Button Pressed");
        });
    }
}
