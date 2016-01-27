import com.nixsolutions.studentgrade.dao.TermDao;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.TermService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by svichkar on 1/27/2016.
 */
public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        TermDao termDao = (TermDao) context.getBean("termDao");
        Term term = new Term();
        term.setTermName("springmother!");
        termDao.create(term);
        System.out.println(termDao.findAll().get(0).getTermName());


        TermService termService = (TermService) context.getBean("termService");
        Term term2 = new Term();
        term2.setTermName("service");
        termService.create(term2);
        System.out.println(termDao.findAll().get(1).getTermName());
    }
}
