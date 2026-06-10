package lab01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrchestratorService implements DisposableBean {

    private final ObjectProvider<RequestHandler> handlerProvider;
    static final AtomicInteger DESTROY_CALLS = new AtomicInteger(0);

    public OrchestratorService(ObjectProvider<RequestHandler> handlerProvider) {
        this.handlerProvider = handlerProvider;
    }

    public Connection process(RequestContext ctx) {
        RequestHandler h = handlerProvider.getObject();
        return h.handle(ctx); // NO cleanup aquí: intencional para demostrar leak/ownership
    }

    public Connection processAndClose(RequestContext ctx) {
        RequestHandler h = handlerProvider.getObject();
        try {
            return h.handle(ctx);
        } finally {
            h.closeRequest();
        }
    }

    public void processAndFail(RequestContext ctx) {
        RequestHandler h = handlerProvider.getObject();
        try {
            h.handle(ctx);
            throw new IllegalStateException("boom");
        } finally {
            h.closeRequest();
        }
    }

    @Override
    public void destroy() throws Exception {

        DESTROY_CALLS.incrementAndGet();
    }
}
