package com.osint.bot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@RestController
public class BotWebController {

    private TelegramBotsLongPollingApplication activeBotManager;
    private final OsintApiService osintService = new OsintApiService();

    @PostMapping("/api/bot/start")
    public synchronized String startBotDynamically(@RequestParam String token) {
        if (activeBotManager != null) {
            try {
                activeBotManager.close();
                System.out.println("🔄 Operational daemon terminated safely to allocate new session.");
            } catch (Exception e) {
                System.err.println("Error clearing previous instance: " + e.getMessage());
            }
        }

        try {
            activeBotManager = new TelegramBotsLongPollingApplication();
            TelegramClient client = new OkHttpTelegramClient(token);

            activeBotManager.registerBot(token, new OsintBotController(client));

            System.out.println("🚀 Web Interface: Daemon successfully instantiated via token payload mapping!");
            return "successfully";
        } catch (Exception e) {
            return "❌ Engine Initialization Failure: " + e.getMessage();
        }
    }

    // New REST Endpoint to query the intelligence data and stream it to the Web UI
    @GetMapping("/api/bot/query")
    public String getIntelDirectlyFromWeb(@RequestParam String ip) {
        try {
            System.out.println("🔍 Processing Web Console request for Target IP Node: " + ip);
            return osintService.fetchIpIntelligence(ip);
        } catch (Exception e) {
            return "❌ FAILURE STATE DETECTED DURING DATA EXTRACTION: " + e.getMessage();
        }
    }
}