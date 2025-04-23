package com.uni.currencyexchange;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        new Thread(() -> SpringApplication.run(Main.class, args)).start();
        SwingUtilities.invokeLater(() -> { new Application(); });
    }

}
