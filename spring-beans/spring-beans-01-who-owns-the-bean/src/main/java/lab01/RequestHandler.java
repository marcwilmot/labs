package lab01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;


@Component
@Scope(SCOPE_PROTOTYPE)
public class RequestHandler implements DisposableBean {

    private Connection connection;
    static final AtomicInteger DESTROY_CALLS = new AtomicInteger(0);

    public Connection handle(RequestContext ctx){


        try{
            connection = DriverManager.getConnection("jdbc:h2:mem:lab01;DB_CLOSE_DELAY=-1", "sa", "");

            try(Statement st = connection.createStatement()){
                st.execute("select 1");

            }
            return connection;
        }catch (Exception e){

            closeRequest();
            throw new IllegalStateException("Failed to handle request " + ctx.getRequestId(), e);
        }

    }

    public void closeRequest(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void destroy(){
        DESTROY_CALLS.incrementAndGet();
    }
}
