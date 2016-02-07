import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Test {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        URI targetUri = UriBuilder.fromUri("http://localhost:8080/web-services").build();

        WebTarget service = client.target(targetUri).path("ws/rest/students").path("getAll");

        Invocation.Builder invocationBuilder = service.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}