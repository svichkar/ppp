package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.AuthorBook;

public interface AuthorBookDao {
	public List<AuthorBook> getAllAuthorBook();
	public List<AuthorBook> getBooksIdByAuthorId(Long authorId);
	public List<AuthorBook> getAuthorIdByBookId(Long bookId);
	public AuthorBook getAuthorBookById(Long authorId, Long bookId);
	public void createAuthorBook(AuthorBook authorBook);
	public void updateAuthorBook(AuthorBook authorBook);
	public void deleteAuthorBook(AuthorBook authorBook);
}
