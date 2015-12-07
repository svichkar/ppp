package database.dao;

import java.util.List;

import database.entities.Term;

public interface TermDao {
	public void create(String alias);

	public void update(Term term);

	public void delete(Term term);

	public Term getByTermId(int termId);
	
	public Term getByTermAlias(String alias);

	public List<Term> getAll();
}
