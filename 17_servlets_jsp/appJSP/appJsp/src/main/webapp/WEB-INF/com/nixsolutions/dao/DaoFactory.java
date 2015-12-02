package com.nixsolutions.dao;

import com.nixsolutions.dao.impl.JournalDaoImpl;
import com.nixsolutions.dao.impl.RoleDaoImpl;
import com.nixsolutions.dao.impl.StatusDaoImpl;
import com.nixsolutions.dao.impl.StudentDaoImpl;
import com.nixsolutions.dao.impl.StudentGroupDaoImpl;
import com.nixsolutions.dao.impl.SubjectDaoImpl;
import com.nixsolutions.dao.impl.TermDaoImpl;
import com.nixsolutions.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static UserDaoImpl getUser() {
	return new UserDaoImpl();
    }
    
    public static TermDaoImpl getTerm() {
	return new TermDaoImpl();
    }
    
    public static SubjectDaoImpl getSubject() {
	return new SubjectDaoImpl();
    }
    
    public static StudentGroupDaoImpl getStudentGroup() {
	return new StudentGroupDaoImpl();
    }
    
    public static StudentDaoImpl getStudent() {
	return new StudentDaoImpl();
    }
    
    public static StatusDaoImpl getStatus() {
	return new StatusDaoImpl();
    }
    
    public static RoleDaoImpl getRole() {
	return new RoleDaoImpl();
    }
    
    public static JournalDaoImpl getJournal() {
	return new JournalDaoImpl();
    }
}
