import com.nixsolutions.studentgrade.service.*;
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
        SubjectService subjectService = (SubjectService) context.getBean("subjectService");
        TermService termService = (TermService) context.getBean("termService");


        journalService.findAll();


        subjectService.findByNameAndTermId("java", 1L);
    }
}
