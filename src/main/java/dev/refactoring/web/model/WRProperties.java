package dev.refactoring.web.model;

import dev.refactoring.web.impl.DefaultHttpResponseErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * The WRProperties class has properties for an HTTP request.
 * @author refactoring
 */
public class WRProperties {
    private final String method;
    private final Map<String, String> headers;
    private final Map<String, String> parameters;
    private final String body;
    private final int connectionTimeout;
    private final int readTimeout;
    private final IHttpResponseErrorHandler errorHandler;

    private WRProperties(Builder builder) {
        this.method = builder.method;
        this.headers = builder.headers;
        this.parameters = builder.parameters;
        this.body = builder.body;
        this.connectionTimeout = builder.connectionTimeout;
        this.readTimeout = builder.readTimeout;
        this.errorHandler = builder.errorHandler;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getBody() {
        return body;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public IHttpResponseErrorHandler getErrorHandler() {
        return errorHandler;
    }

    /**
     * The Builder class for constructing WRProperties objects.
     * @author refactoring
     */
    public static class Builder {
        private String method;
        private Map<String, String> headers;
        private Map<String, String> parameters;
        private String body;
        private int connectionTimeout = 5000;
        private int readTimeout = 10000;
        private IHttpResponseErrorHandler errorHandler = new DefaultHttpResponseErrorHandler();

        /**
         * Constructs a Builder with the required properties.
         *
         * @param method The HTTP method (e.g., GET, POST).
         */
        public Builder(String method) {
            this.method = method;
        }

        /**
         * Sets the headers for the request.
         *
         * @param headers The headers map.
         * @return The Builder instance for method chaining.
         */
        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        /**
         * Adds a header to the request.
         *
         * @param key   The header key.
         * @param value The header value.
         * @return The Builder instance for method chaining.
         */
        public Builder addHeader(String key, String value) {
            if (this.headers == null) {
                this.headers = new HashMap<>();
            }
            this.headers.put(key, value);
            return this;
        }

        /**
         * Sets the query parameters for the request.
         *
         * @param parameters The query parameters map.
         * @return The Builder instance for method chaining.
         */
        public Builder parameters(Map<String, String> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Adds a query parameter to the request.
         *
         * @param key   The parameter key.
         * @param value The parameter value.
         * @return The Builder instance for method chaining.
         */
        public Builder addParameter(String key, String value) {
            if (this.parameters == null) {
                this.parameters = new HashMap<>();
            }
            this.parameters.put(key, value);
            return this;
        }

        /**
         * Sets the body for the request.
         *
         * @param body The request body.
         * @return The Builder instance for method chaining.
         */
        public Builder body(String body) {
            this.body = body;
            return this;
        }

        /**
         * Sets the connection timeout for the request.
         *
         * @param connectionTimeout The connection timeout in milliseconds.
         * @return The Builder instance for method chaining.
         */
        public Builder connectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * Sets the read timeout for the request.
         *
         * @param readTimeout The read timeout in milliseconds.
         * @return The Builder instance for method chaining.
         */
        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * Sets the error handler for handling HTTP error responses.
         *
         * @param errorHandler The error handler.
         * @return The Builder instance for method chaining.
         */
        public Builder errorHandler(IHttpResponseErrorHandler errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        /**
         * Builds the WRProperties object.
         *
         * @return The constructed WRProperties object.
         */
        public WRProperties build() {
            return new WRProperties(this);
        }
    }
}