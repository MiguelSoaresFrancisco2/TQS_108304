package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SimpleHttpClient implements ISimpleHttpClient {

    @Override
    public String doHttpGet(String urlString) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error Code: " + conn.getResponseCode());
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

        } catch (IOException e) {
            throw new RuntimeException("Error during HTTP request: " + e.getMessage(), e);
        }

        return response.toString();
    }
}
