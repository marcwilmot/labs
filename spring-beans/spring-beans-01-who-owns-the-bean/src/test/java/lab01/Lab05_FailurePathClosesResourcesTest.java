package lab01;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import static org.junit.jupiter.api.Assertions.assertThrows;

public class Lab05_FailurePathClosesResourcesTest {

    @Test
    void failurePath_stillClosesResources(){

        try (var ctx = new AnnotationConfigApplicationContext(LabConfig.class)){

            OrchestratorService orchestratorService = ctx.getBean(OrchestratorService.class);

            assertThrows(IllegalStateException.class,
                    () -> orchestratorService.processAndFail(new RequestContext("boom")));
        }
    }
}
