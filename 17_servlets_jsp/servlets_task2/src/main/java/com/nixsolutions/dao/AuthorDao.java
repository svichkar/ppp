package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Author;

public interface AuthorDao {
	public List<Author> getAllAuthors();
	public List<Author> getAuthorsByName(String name);
	public Author getAuthorById(int authorId);
	public void createAuthor(Author author);
	public void updateAuthor(Author author);
	public void deleteAuthor(Author author);
}
