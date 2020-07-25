package com.levoncorp.bitty.forecast.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public interface CurrencyChecker {
    default String makeRequest(String urlApi) throws IOException {
        URL url = new URL(urlApi);
        URLConnection connection = url.openConnection();
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inLine;
        StringBuilder content = new StringBuilder();
        while ((inLine = bf.readLine()) != null) {
            content.append(inLine);
        }

        return content.toString();
    }

    double currentValue() throws IOException;
}
