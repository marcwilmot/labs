package lab01;

import java.util.Objects;
import java.util.UUID;

public class RequestContext {
    private final String requestId;
    private final String payload;

    public RequestContext(String payload) {
        this.requestId = UUID.randomUUID().toString();
        this.payload = Objects.requireNonNull(payload, "payload");
    }

    public String getRequestId() {
        return requestId;
    }

    public String getPayload() {
        return payload;
    }
}
