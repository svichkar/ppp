package servlet.manager.student;

import bean.StudentBean;
import database.dao.*;
import database.entity.Student;
import database.dao.impl.H2DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/Students.do")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TermDao termDao;
	private StudentDao studentDao;
	private StatusDao statusDao;
	private StudentGroupDao studentGroupDao;

	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			termDao = daoFactory.getTermDao();
			statusDao = daoFactory.getStatusDao();
			studentGroupDao = daoFactory.getStudentGroupDao();
			studentDao = daoFactory.getStudentDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<StudentBean> students = new ArrayList<>();
		List<Student> studentsTemp = studentDao.getAll();
		for (Student student : studentsTemp) {
			students.add(new StudentBean(student.getId(), student.getFirstName(), student.getLastName(),
					student.getDateBirthday(), student.getDateEntry(),
					studentGroupDao.getByStudentGroupId(student.getStudentGroupId()).getStudentGroupName(),
					termDao.getByTermId(student.getTermId()).getAlias(),
					statusDao.getByStatusId(student.getStatusId()).getStatusName()));
		}
		request.setAttribute("students", students);
		request.setAttribute("groups", studentGroupDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/Students.jsp");
		rs.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<StudentBean> students = new ArrayList<>();
		List<Student> studentsTemp = new ArrayList<>();
		String firstName = request.getParameter("firstName");
		if (firstName != null) {
			String lastName = request.getParameter("lastName");
			studentsTemp.addAll(studentDao.getByStudentsByName(firstName, lastName));
		} else {
			String group = request.getParameter("studentGroup");
			studentsTemp.addAll(studentDao.getStudentsByGroup(studentGroupDao.getByStudentGroupName(group).getId()));
		}
		students.addAll(studentsTemp.stream()
				.map(student -> new StudentBean(student.getId(), student.getFirstName(), student.getLastName(),
						student.getDateBirthday(), student.getDateEntry(),
						studentGroupDao.getByStudentGroupId(student.getStudentGroupId()).getStudentGroupName(),
						termDao.getByTermId(student.getTermId()).getAlias(),
						statusDao.getByStatusId(student.getStatusId()).getStatusName()))
				.collect(Collectors.toList()));
		request.setAttribute("students", students);
		request.setAttribute("groups", studentGroupDao.getAll());
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/student/Students.jsp");
		rs.forward(request, response);
	}

}
