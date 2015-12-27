package servlet.manager.journal;

import bean.JournalBean;
import database.dao.*;
import database.entity.Journal;
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

@WebServlet("/Journal.do")
public class JournalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDao subjectDao;
	private JournalDao journalDao;
	private StudentDao studentDao;
	private StudentGroupDao studentGroupDao;
	private RateDao rateDao;

	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();
			subjectDao = daoFactory.getSubjectDao();
			journalDao = daoFactory.getJournalDao();
			studentDao = daoFactory.getStudentDao();
			studentGroupDao = daoFactory.getStudentGroupDao();
			rateDao = daoFactory.getRateDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<JournalBean> journal = new ArrayList<>();
		List<Journal> journalTemp = journalDao.getAll();
		for (Journal recordTemp : journalTemp) {
			Student student = studentDao.getByStudentId(recordTemp.getStudentId());
			journal.add(new JournalBean(student.getFirstName(), student.getLastName(),
					studentGroupDao.getByStudentGroupId(student.getStudentGroupId()).getStudentGroupName(),
					subjectDao.getBySubjectId(recordTemp.getSubjectId()).getName(),
					rateDao.getByRateId(recordTemp.getRateId()).getRateValue().toString()));
		}
		request.setAttribute("journal", journal);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/journal/Journal.jsp");
		rs.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<JournalBean> journal = new ArrayList<>();
		String tempId = request.getParameter("studentId");
		int id = Integer.parseInt(tempId);
		List<Journal> journalTemp = journalDao.getJournalByStudentId(id);
		for (Journal recordTemp : journalTemp) {
			Student student = studentDao.getByStudentId(recordTemp.getStudentId());
			journal.add(new JournalBean(student.getFirstName(), student.getLastName(),
					studentGroupDao.getByStudentGroupId(student.getStudentGroupId()).getStudentGroupName(),
					subjectDao.getBySubjectId(recordTemp.getSubjectId()).getName(),
					rateDao.getByRateId(recordTemp.getRateId()).getRateValue().toString()));
		}
		request.setAttribute("journal", journal);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/journal/Journal.jsp");
		rs.forward(request, response);
	}

}
