package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.entity.AuthorBook;

public interface AuthorBookDao {
	public List<AuthorBook> getAllAuthorBook();
	public List<AuthorBook> getBooksIdByAuthorId(int authorId);
	public AuthorBook getAuthorBookById(int authorId, int bookId);
	public void createAuthorBook(AuthorBook authorBook);
	public void deleteAuthorBook(AuthorBook authorBook);
}
