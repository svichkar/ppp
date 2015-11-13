package customservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class CustomServlet extends HttpServlet {

	private static final long serialVersionUID = 2795446699089557239L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\n" + "<html>\n\t" + "<head>\n\t\t<title>Test servlet</title>\n\t</head>\n\t"
				+ "<body bgcolor=\"#00dada\">\n\t\t" + "<h1> Test Message </h1>\n\t\t"
				+ "<p> Simple servlet for testing <p>\n\t" + "</body>\n</html>");
		out.close();
	}

}
