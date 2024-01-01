package dev.refactoring.web.impl;

import dev.refactoring.web.model.IHttpResponseErrorHandler;

import java.util.Map;

/**
 * A default implementation of IHttpResponseErrorHandler that prints the error details to the console.
 * @author refactoring
 */
public class DefaultHttpResponseErrorHandler implements IHttpResponseErrorHandler {
    @Override
    public void handleError(int statusCode, String responseBody, Map<String, String> responseHeaders) {
        System.err.println("HTTP Error " + statusCode);
        System.err.println("Response Body: " + responseBody);
        System.err.println("Response Headers: " + responseHeaders);
    }
}