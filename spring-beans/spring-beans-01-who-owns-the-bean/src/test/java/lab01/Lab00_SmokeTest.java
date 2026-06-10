package lab01;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Lab00_SmokeTest {

    @Test
    void contextStart() {
        try (var ctx = new AnnotationConfigApplicationContext(LabConfig.class)) {
            System.out.println("Context started: " + ctx.getBeanDefinitionCount());
        }
    }
}
