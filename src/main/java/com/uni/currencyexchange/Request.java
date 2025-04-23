package com.uni.currencyexchange;

import javax.swing.JOptionPane;

import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
    private static final String URL = "https://open.er-api.com/v6/latest/EUR";
    private WebClient.Builder builder;
    private String response;
    private ObjectMapper mapper;
    private JsonNode root;
    private JsonNode rates;

    public void sendRequest() {
        builder = WebClient.builder();
        response = builder.build()
                .get()
                .uri(URL)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
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
