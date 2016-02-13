import com.nixsolutions.studentgrade.model.User;
import com.nixsolutions.studentgrade.service.StudentService;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

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
        marshaller.marshal(list, System.out);

        Client client = ClientBuilder.newClient();
        URI targetUri = UriBuilder.fromUri("http://localhost:8080/web-services").build();

        WebTarget service = client.target(targetUri).path("ws/rest/students").path("createStudent");

        Invocation.Builder invocationBuilder = service.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.post(Entity.entity(student,MediaType.APPLICATION_XML_TYPE));

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
*/
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        SchemaOutputResolver sor = new MySchemaOutputResolver();
        jaxbContext.generateSchema(sor);
        sor.createOutput("web-services.com", "user");
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

