package database.dao;

import java.util.List;

import database.entities.Subject;


public interface SubjectDao {
	public void create(String name, int termId);

	public void update(Subject subject);

	public void delete(Subject subject);

	public Subject getBySubjectId(int subjectId);
	
	public Subject getBySubjectName(String name, int termId);
	
	public List<Subject> getAll();
	
	public List<Subject> getSubjectsByTermId(int termId);
}
