package dev.refactoring.web.model;

import java.util.Map;

/**
 * An interface for handling HTTP error responses.
 * @author refactoring
 */
public interface IHttpResponseErrorHandler {
    void handleError(int statusCode, String responseBody, Map<String, String> responseHeaders);
}
