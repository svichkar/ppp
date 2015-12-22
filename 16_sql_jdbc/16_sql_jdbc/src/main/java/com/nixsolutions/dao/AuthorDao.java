package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Author;

public interface AuthorDao {
	public List<Author> getAllAuthors();
	public Author getAuthor(int authorId);
	public void createAuthor(Author author);
	public void updateAuthor(Author author);
	public void deleteAuthor(Author author);
}
