package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entity.Author;

public interface AuthorService {
	public List<Author> getAllAuthors();
	public Author getAuthorByName(String name);
	public Author getAuthorById(Long authorId);
	public Author createAuthor(Author author);
	public void updateAuthor(Author author);
	public void deleteAuthor(Author author);
}
