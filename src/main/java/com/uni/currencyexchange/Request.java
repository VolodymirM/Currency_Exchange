package com.uni.currencyexchange;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
    private static final String URL = "https://open.er-api.com/v6/latest/EUR";
    private WebClient builder;
    private String response;
    private ObjectMapper mapper;
    private JsonNode root;
    private JsonNode rates;

    public void sendRequest() {
        try {
            builder = WebClient.builder();
            response = builder.get().uri(URL).block();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                null,
                "Something went wrong while fetching the data...",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
            
            return;
        }
        
        try {
            mapper = new ObjectMapper();
            root = mapper.readTree(response);
            rates = root.path("rates");
        } catch (JsonProcessingException e) {
            JOptionPane.showMessageDialog(
                null,
                "Something went wrong while fetching the data...",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public double getRate(String currency) {
        return rates.path(currency).asDouble();
    }
}
