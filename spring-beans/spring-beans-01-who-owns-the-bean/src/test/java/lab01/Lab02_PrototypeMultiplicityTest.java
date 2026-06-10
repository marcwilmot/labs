package lab01;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Lab02_PrototypeMultiplicityTest {

    @Test
    void orchestratorIsSingletonByDefault (){
        try(var ctx = new AnnotationConfigApplicationContext((LabConfig.class))){
            RequestHandler a = ctx.getBean(RequestHandler.class);
            RequestHandler b = ctx.getBean(RequestHandler.class);

            assertNotSame(a, b);

        }
    }
}
