package lab01;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertSame;

public class Lab01_SingletonIdentityTest {

    @Test
    void orchestratorIsSingletonByDefault (){
        try(var ctx = new AnnotationConfigApplicationContext((LabConfig.class))){
            OrchestratorService a = ctx.getBean(OrchestratorService.class);
            OrchestratorService b = ctx.getBean(OrchestratorService.class);

            assertSame(a, b);

        }
    }
}
