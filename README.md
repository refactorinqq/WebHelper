
# Web Helper

Simple library to help you make web requests.

### Features: 

- Full control
- Default error handling, with the option to make your own error handler.
- Secure and fast

### Including in your project:

The project is distributed via https://jitpack.io

```groovy
repositories {
	maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.refactorinqq:WebHelper:-SNAPSHOT'
}
```
### Example:

```java
try {
    WRProperties properties = new WRProperties.Builder("POST")
            .addHeader("Content-Type", "application/json")
            .addParameter("param1", "value1")
            .body("{\"key\": \"value\"}")
            .connectionTimeout(8000) // optional
            .readTimeout(15000) // optional
            .errorHandler(new CustomHttpResponseErrorHandler()) // optional
            .build();

    WResponse response = WRequest.sendRequest("https://example.com/api", properties);

    System.out.println("Status Code: " + response.getStatusCode());
    System.out.println("Response Body: " + response.getBody());
    System.out.println("Response Headers: " + response.getHeaders());
} catch (IOException e) {
    e.printStackTrace();
}
```

### Error handling

The library comes with a default error handler, but some people like to do their own thing.

You can just implement the `IHttpResponseErrorHandler` interface to make your own error handler.

#### Example: 

```java
class CustomHttpResponseErrorHandler implements HttpResponseErrorHandler {
    @Override
    public void handleError(int statusCode, String responseBody, Map<String, String> responseHeaders) {
        // Implement custom error handling logic, e.g., log to a file
        System.err.println("Custom Error Handling for HTTP Error " + statusCode);
        System.err.println("Response Body: " + responseBody);
        System.err.println("Response Headers: " + responseHeaders);
    }
}
```
### Contributing

Contributions are always welcome!

Fork the repo, make your changes, open a PR with a brief description of your changes.
### License

[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-green.svg)](https://opensource.org/licenses/)

