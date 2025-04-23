package com.uni.currencyexchange;

public class Convertion {
    private String fromCurrency;
    private String toCurrency;
    private Double amount;
    private final Request request;

    Convertion() {
        this.fromCurrency = "USD";
        this.toCurrency = "USD";
        this.amount = 0.0;
        request = new Request();
    }

    public double calculate() {
        request.sendRequest();
        return request.getRate(toCurrency) / request.getRate(fromCurrency) * amount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
