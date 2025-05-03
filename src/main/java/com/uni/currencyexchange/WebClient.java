package com.uni.currencyexchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {

    private String method = "GET";
    private String urlString;

    private WebClient() {}

    public static WebClient builder() {
        return new WebClient();
    }

    public WebClient get() {
        this.method = "GET";
        return this;
    }

    public WebClient uri(String url) {
        this.urlString = url;
        return this;
    }

    public String block() throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        BufferedReader reader;

        if (status >= 200 && status < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();
        conn.disconnect();

        if (status < 200 || status >= 300) {
            throw new IOException("HTTP error: " + status + " - " + content);
        }

        return content.toString();
    }
}
