//import javassist.ClassPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.borgatin.common.model.LessonModel;


public class ActionServer {
    public static void main(String[] args) {

        new ClassPathXmlApplicationContext("xml-bean.xml" );
//        new ClassPathXmlApplicationContext("mvc-context.xml" );

    }
}
