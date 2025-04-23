package com.uni.currencyexchange;

public class Application {
    private static UI ui;
    private static Convertion conversion;

    public void start() {
        conversion = new Convertion();
        ui = new UI();

        ui.setFromDropdown((String currency) -> {
            conversion.setFromCurrency(currency);
            System.out.println("From Currency: " + currency);
        });
        
        ui.setToDropdown((String currency) -> {
            conversion.setToCurrency(currency);
            System.out.println("To Currency: " + currency);
        });
        
        ui.changeAmount((String amountStr) -> {
            try {
                conversion.setAmount(0.0);
                conversion.setAmount(Double.valueOf(amountStr));
                System.out.println("Amount: " + conversion.getAmount());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered: " + amountStr);
            }
        });
        
        ui.buttonPressed(() -> {
            if (conversion.getAmount() <= 0.0) {
                ui.changeResult(null);
                return;
            }

            ui.changeResult(conversion.calculate());
            System.out.println("Button Pressed");
        });
    }
}
