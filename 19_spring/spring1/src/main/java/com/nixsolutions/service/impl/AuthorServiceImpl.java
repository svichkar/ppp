package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.AuthorDao;
import com.nixsolutions.entity.Author;
import com.nixsolutions.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorDao authorDao;
	
	@Override
	public List<Author> getAllAuthors() {
		return authorDao.getAllAuthors();
	}

	@Override
	public Author getAuthorByName(String name) {
		return authorDao.getAuthorByName(name);
	}

	@Override
	public Author getAuthorById(Long authorId) {
		return authorDao.getAuthorById(authorId);
	}

	@Override
	public Author createAuthor(Author author) {
		return authorDao.createAuthor(author);
	}

	@Override
	public void updateAuthor(Author author) {
		authorDao.updateAuthor(author);
	}

	@Override
	public void deleteAuthor(Author author) {
		authorDao.deleteAuthor(author);
	}

	
	
}
