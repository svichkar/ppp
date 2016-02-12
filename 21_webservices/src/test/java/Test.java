import com.nixsolutions.studentgrade.model.AllStudentsBean;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.net.URI;

@Transactional
public class Test {


    static StudentService studentService;

    public static void main(String[] args) throws JAXBException {

        studentService = (StudentService) new ClassPathXmlApplicationContext("root-context.xml").getBean("studentService");
        Student student = studentService.findById(1L);
        AllStudentsBean list = new AllStudentsBean();
        list.setStudents(studentService.findAll());
        JAXBContext jc = JAXBContext.newInstance(AllStudentsBean.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(list, System.out);

        Client client = ClientBuilder.newClient();
        URI targetUri = UriBuilder.fromUri("http://localhost:8080/web-services").build();

        WebTarget service = client.target(targetUri).path("ws/rest/students").path("createStudent");

        Invocation.Builder invocationBuilder = service.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.post(Entity.entity(student,MediaType.APPLICATION_XML_TYPE));

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}

