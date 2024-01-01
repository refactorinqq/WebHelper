package dev.refactoring.web;

import dev.refactoring.web.model.WRProperties;
import dev.refactoring.web.model.WResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The WRequest class helps you make HTTP requests.
 * @author refactoring
 */
public class WRequest {

    /**
     * Makes an HTTP request to the specified URL.
     *
     * @param url        The URL to make the request to.
     * @param properties The properties of the request.
     * @return A WResponse object containing the response information.
     */
    public static WResponse sendRequest(String url, WRProperties properties) throws IOException {
        URL requestUrl = new URL(buildUrlWithParameters(url, properties.getParameters()));
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

        connection.setRequestMethod(properties.getMethod());

        connection.setConnectTimeout(properties.getConnectionTimeout());
        connection.setReadTimeout(properties.getReadTimeout());

        if (properties.getHeaders() != null) {
            for (Map.Entry<String, String> entry : properties.getHeaders().entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (properties.getBody() != null && (properties.getMethod().equals("POST") || properties.getMethod().equals("PUT"))) {
            connection.setDoOutput(true);
            connection.getOutputStream().write(properties.getBody().getBytes("UTF-8"));
        }

        int responseCode = connection.getResponseCode();
        Map<String, String> responseHeaders = extractResponseHeaders(connection);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        if (responseCode >= 400) {
            properties.getErrorHandler().handleError(responseCode, response.toString(), responseHeaders);
        }

        return new WResponse(responseCode, response.toString(), responseHeaders);
    }

    private static String buildUrlWithParameters(String url, Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return url;
        }

        StringBuilder urlWithParameters = new StringBuilder(url);
        if (!url.contains("?")) {
            urlWithParameters.append("?");
        } else {
            urlWithParameters.append("&");
        }

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            urlWithParameters.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        return urlWithParameters.toString();
    }

    private static Map<String, String> extractResponseHeaders(HttpURLConnection connection) {
        Map<String, String> headers = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                headers.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        return headers;
    }
}
