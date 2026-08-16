package com.osint.bot;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.regex.Pattern;

public class OsintBotController implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final OsintApiService osintService;

    // Regex to validate simple IPv4 address formatting
    private static final Pattern IP_PATTERN = Pattern.compile(
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    );

    public OsintBotController(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
        this.osintService = new OsintApiService();
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String incomingText = update.getMessage().getText().trim();
            long chatId = update.getMessage().getChatId();

            if (incomingText.startsWith("/start")) {
                sendReply(chatId, "👋 Welcome to the OSINT IP Analyzer Bot!\nSend me any valid public IPv4 address (e.g., `8.8.8.8`) to analyze.");
                return;
            }

            String ipAddress = incomingText.replace("/check", "").trim();

            if (!IP_PATTERN.matcher(ipAddress).matches()) {
                sendReply(chatId, "❌ Invalid IPv4 address format. Please try again with a valid format.");
                return;
            }

            sendReply(chatId, "🔍 Fetching open-source intelligence records for: " + ipAddress + "...");

            try {
                String reportMarkdown = osintService.fetchIpIntelligence(ipAddress);
                sendReply(chatId, reportMarkdown);
            } catch (Exception e) {
                sendReply(chatId, "⚠️ Internal Error occurred while querying threat databases: " + e.getMessage());
            }
        }
    }

    private void sendReply(long chatId, String responseMarkdown) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(responseMarkdown)
                .parseMode("Markdown")
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            System.err.println("Failed to drop chat delivery payload: " + e.getMessage());
        }
    }
}