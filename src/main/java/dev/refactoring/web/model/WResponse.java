package dev.refactoring.web.model;

import java.util.Map;

/**
 * The WResponse class represents the response of an HTTP request.
 * @author refactoring
 */
public class WResponse {

    private final int statusCode;
    private final String body;
    private final Map<String, String> headers;

    /**
     * Constructs a WResponse with the specified status code, body, and response headers.
     *
     * @param statusCode The HTTP status code.
     * @param body       The response body.
     * @param headers    The response headers.
     */
    public WResponse(int statusCode, String body, Map<String, String> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    /**
     * Gets the HTTP status code of the response.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Gets the body of the response.
     *
     * @return The response body.
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the response headers.
     *
     * @return The response headers.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }
}