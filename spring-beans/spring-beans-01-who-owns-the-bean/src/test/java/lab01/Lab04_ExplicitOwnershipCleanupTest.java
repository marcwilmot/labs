package lab01;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lab04_ExplicitOwnershipCleanupTest {

    @Test
    void closingApplicationContext_doesNotCloseConnectionCreatedInsidePrototype() throws Exception{

        try(var ctx = new AnnotationConfigApplicationContext(LabConfig.class)) {
            OrchestratorService orchestratorService = ctx.getBean(OrchestratorService.class);

            Connection con = orchestratorService.processAndClose(new RequestContext("payload"));

            assertTrue(con.isClosed(), "Ownership explicit cleanup: connection is closed.");
        }
    }
}
