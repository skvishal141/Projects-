package com.osint.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        // This launches Spring Boot's internal web pipeline engine wrapper instead of raw console loops
        SpringApplication.run(MainApp.class, args);
        System.out.println("\n🖥️ ========================================================");
        System.out.println("🚀 Web Control Panel dashboard interface execution layer is LIVE!");
        System.out.println("🔗 Open your browser and navigate to: http://localhost:8080");
        System.out.println("============================================================");
    }
}