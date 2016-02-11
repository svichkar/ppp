import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.webservice.provider.SqlDateAdapter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.net.URI;
import java.util.List;

public class Test {


    static StudentService studentService;


    public static void main(String[] args) throws JAXBException {


        studentService = (StudentService) new ClassPathXmlApplicationContext("root-context.xml").getBean("studentService");
        Student student = studentService.findById(1L);
        List<Student> list = studentService.findAll();
        JAXBContext jc = JAXBContext.newInstance(list.getClass());
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(list, System.out);




        SqlDateAdapter a = new SqlDateAdapter();

        Client client = ClientBuilder.newClient();
        URI targetUri = UriBuilder.fromUri("http://localhost:8080/web-services").build();

        WebTarget service = client.target(targetUri).path("ws/rest/students").path("getStudent/1?fmt=json");

        Invocation.Builder invocationBuilder = service.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}