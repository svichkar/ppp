import com.nixsolutions.studentgrade.service.JournalService;
import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by svichkar on 1/27/2016.
 */
public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

        StudentService studentService = (StudentService) context.getBean("studentService");
        StatusService statusService = (StatusService) context.getBean("statusService");
        JournalService journalService = (JournalService) context.getBean("journalService");


        journalService.findAll();

    }
}
