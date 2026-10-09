package com.osint.bot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OsintApiService {

    private final HttpClient httpClient;
    private final ObjectMapper jsonParser;

    public OsintApiService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.jsonParser = new ObjectMapper();
    }

    public String fetchIpIntelligence(String ipAddress) throws Exception {
        String queryUrl = "http://ip-api.com/json/" + ipAddress + "?fields=status,message,country,city,isp,as,query";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(queryUrl))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("API endpoint returned error status: " + response.statusCode());
        }

        JsonNode rootNode = jsonParser.readTree(response.body());

        if ("fail".equals(rootNode.path("status").asText())) {
            return "❌ API Error: " + rootNode.path("message").asText("Unknown look-up failure profile.");
        }

        return "*🌐 OSINT IP INTEL REPORT*\n\n" +
                "• *Target IP Address:* `" + rootNode.path("query").asText() + "`\n" +
                "• *Geographic Country:* " + rootNode.path("country").asText("Unknown") + "\n" +
                "• *City Location:* " + rootNode.path("city").asText("Unknown") + "\n" +
                "• *Assigned ISP Network:* " + rootNode.path("isp").asText("Unknown") + "\n" +
                "• *System Routing ASN:* `" + rootNode.path("as").asText("Unknown") + "`\n\n" +
                "_Data parsed via open public OSINT feeds._";
    }
}