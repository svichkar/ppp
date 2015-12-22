package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.AuthorBook;

public interface AuthorBookDao {
	public List<AuthorBook> getAllAuthorBook();
	public AuthorBook getAuthorBook(int authorBookId);
	public void createAuthorBook(AuthorBook authorBook);
	public void updateAuthorBook(AuthorBook authorBook);
	public void deleteAuthorBook(AuthorBook authorBook);
}
