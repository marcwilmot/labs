package lab01;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Lab06_SingletonDestructionCalled {

    @Test
    public void closingApplicationContext_DoesNotDestroyPrototype() throws SQLException {

        var ctx = new AnnotationConfigApplicationContext(LabConfig.class);

        Connection con;

        try{
            OrchestratorService orchestratorService = ctx.getBean(OrchestratorService.class);

            con = orchestratorService.process(new RequestContext("payload"));

            assertEquals(0, OrchestratorService.DESTROY_CALLS.get(),
                    "sanity: destroy() not called during normal use");
        } finally {
            ctx.close();
        }
        // 1) Recurso NO cerrado
        assertFalse(con.isClosed(),
                "Context closed, but JDBC Connection remains open because Spring doesn't own it.");

        // 2) Prototype NO destruido (aunque tenga hook destroy())
        assertEquals(1, OrchestratorService.DESTROY_CALLS.get(),
                "Context closed, and destroy hook called bc is Singleton");


        con.close();
    }
}
