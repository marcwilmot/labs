package lab01;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class Lab03_PrototypeDestructionLimitTest {

    @Test
    void closingApplicationContext_doesNotCloseConnectionCreatedInsidePrototype() throws Exception{

        Connection con;

        var ctx = new AnnotationConfigApplicationContext(LabConfig.class);

        try{
            OrchestratorService orchestratorService = ctx.getBean(OrchestratorService.class);

            con = orchestratorService.process(new RequestContext("Payload"));
            assertFalse(con.isClosed(), "sanity: connection starts open");
        } finally {
            ctx.close();
        }

        assertFalse(con.isClosed(), "Context is closed, but de JDBC connection remains open:  Spring created the bean not owner the resource.");

        con.close();

    }
}
