package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.Author;

public interface AuthorDao {
	 List<Author> getAllAuthors();
	 Author getAuthorByName(String name);
	 Author getAuthorById(Long authorId);
	 Author createAuthor(Author author);
	 void updateAuthor(Author author);
	 void deleteAuthor(Author author);
}
