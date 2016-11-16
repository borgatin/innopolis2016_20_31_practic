import javassist.ClassPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.borgatin.server.config.JpaRepositoriesConfig;

/**
 * Created by avborg on 10.11.2016.
 */
public class ActionServer {
    public static void main(String[] args) {
/*        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JpaRepositoriesConfig.class);
        ctx.refresh();*/
        new ClassPathXmlApplicationContext("mvc-context.xml" );

    }
}
