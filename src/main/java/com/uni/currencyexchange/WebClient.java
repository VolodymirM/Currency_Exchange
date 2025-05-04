package com.uni.currencyexchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class WebClient {
    private String url;
    private int port;

    public WebClient(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public String getResponse() throws IOException {
        URL parsedUrl = new URL(url);
        String host = parsedUrl.getHost();
        String path = parsedUrl.getPath();

        StringBuilder response = new StringBuilder();

        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print("GET " + path + " HTTP/1.1\r\n");
            out.print("Host: " + host + "\r\n");
            out.print("Connection: close\r\n");
            out.print("\r\n");
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            boolean headersEnded = false;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    headersEnded = true;
                    break;
                }
            }

            if (headersEnded)
                while ((line = reader.readLine()) != null)
                    response.append(line).append("\n");

        }

        removeFirstAndLastLine(response);

        System.out.println("Response: " + response.toString());
        return response.toString();
        
    }

    private static void removeFirstAndLastLine(StringBuilder sb) {

        if (sb.length() == 0)
            return;

        String[] lines = sb.toString().split("\n");
    
        if (lines.length <= 2) {
            sb.setLength(0);
            return;
        }
    
        sb.setLength(0);
    
        for (int i = 1; i < lines.length - 1; i++)
            sb.append(lines[i]).append("\n");

    }    

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
