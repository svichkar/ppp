package com.nixsolutions.entity;

import java.util.List;

import com.nixsolutions.dao.impl.AuthorDaoImpl;

public class QuickTest {

	public static void main(String[] args) {
		Author auth = new AuthorDaoImpl().getAuthor(5);
		System.out.println(auth.getAuthorId());
		System.out.println(auth.getFirstName());
		System.out.println(auth.getSecondName());

		List<Author> author = new AuthorDaoImpl().getAllAuthors();
		
		for (Author author2 : author) {
			System.out.println(author2.getAuthorId());
			System.out.println(author2.getFirstName());
			System.out.println(author2.getSecondName());
		}
		
		
		Author authMiha = new Author();
		authMiha.setFirstName("Michail");
		authMiha.setSecondName("Lelyakov");
		
		new AuthorDaoImpl().createAuthor(authMiha);
		
		
		//Author authMiha = new AuthorDaoImpl().getAuthor(11);
		//new AuthorDaoImpl().deleteAuthor(authMiha);
		Author authMih = new AuthorDaoImpl().getAuthor(14);
		authMih.setFirstName("Mihasya");
		authMih.setSecondName("Lelyla");
		new AuthorDaoImpl().updateAuthor(authMih);
	}

}
