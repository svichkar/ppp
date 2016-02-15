import com.nixsolutions.studentgrade.model.AllStudentsBean;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Transactional
public class Test {


    static StudentService studentService;

    public static void main(String[] args) throws JAXBException, IOException {

        /*studentService = (StudentService) new ClassPathXmlApplicationContext("root-context.xml").getBean("studentService");
        Student student = studentService.findById(1L);
        AllStudentsBean list = new AllStudentsBean();
        list.setStudents(studentService.findAll());
        JAXBContext jc = JAXBContext.newInstance(AllStudentsBean.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(list, System.out);*/

       /* Client client = ClientBuilder.newClient();
        WebTarget service = client.target("http://localhost:8080/web-services").path("ws/rest/students").path("getAllStudents");

        Invocation.Builder invocationBuilder = service.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(Student.class));
*/

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        SchemaOutputResolver sor = new MySchemaOutputResolver();
        jaxbContext.generateSchema(sor);
        sor.createOutput("web-services.com", "user");

        String URL = "http://localhost:8080/web-services/ws/rest/students";
        Client client = ClientBuilder.newClient();
        WebTarget service = client.target(URL);

        List<Student> list = service.path("getAllStudents").request(MediaType.APPLICATION_XML).get()
                .readEntity(AllStudentsBean.class).getStudents();


        List<Student> studentList = service.path("getStudentByLastNameAndGroup").queryParam("lastName", "dodson")
                .queryParam("groupName", "java 15-4")
                .request().get().readEntity(AllStudentsBean.class).getStudents();


        service.path("getStudent").queryParam("studentId", "1").request().get().readEntity(Student.class);
    }

    public static class MySchemaOutputResolver extends SchemaOutputResolver {

        public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
            File file = new File(suggestedFileName);
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toURL().toString());
            return result;
        }

    }
}

