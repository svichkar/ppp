package database.dao;

import java.util.List;

import database.entity.Subject;


public interface SubjectDao {
	public void create(String name, int termId);

	public void update(Subject subject);

	public void delete(Subject subject);

	public Subject getBySubjectId(int subjectId);
	
	public List<Subject> getBySubjectName(String name);
	
	public List<Subject> getAll();
	
	public List<Subject> getSubjectsByTermId(int termId);
}
