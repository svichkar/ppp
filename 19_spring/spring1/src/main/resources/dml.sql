--category
INSERT INTO category (name) VALUES ('scifi'), ('horror'), ('music'), ('history'), ('biography'), ('fantasy'), ('children'), ('cookbooks'), ('classic');
--cell
INSERT INTO cell (name) VALUES ('cell A'), ('cell B'), ('cell C'), ('cell D');
--author
INSERT INTO author (first_name, last_name) VALUES ('Stephen', 'King'), ('Johny', 'Hiland'), ('Greg', 'Koch'), ('J.R.R.', 'Tolkien'), ('Erich Maria', 'Remarque'), ('Mikhail', 'Bulgakov'), ('Rick', 'Remender'), ('Brian', 'Vaughan'), ('Anthony', 'Kiedis'), ('Ozzy', 'Osbourne');

--book
--King
INSERT INTO book (name, cell_id, category_id, count) VALUES ('The shining', 1, 2, 3), ('It', 1, 2, 1), ('The green mile', 1, 2, 5), ('Christine', 1, 2, 3), ('Carrie', 1, 2, 2);
--Hiland
INSERT INTO book (name, cell_id, category_id, count) VALUES ('Chickin'' Pickin''', 1, 3, 2), ('Strictly Rhythm', 1, 3, 1), ('Licks and Tricks', 1, 3, 2);
--Koch
INSERT INTO book (name, cell_id, category_id, count) VALUES ('Blues Guitar', 1, 3, 3), ('Lead Licks', 1, 3, 1), ('Guitar gristle', 1, 3, 4);
--Tolkien
INSERT INTO book (name, cell_id, category_id, count) VALUES ('The Silmarillion', 1, 6, 2), ('The Hobbit', 1, 6, 4), ('The Lord of the Rings', 1, 6, 2);
--Remarque
INSERT INTO book (name, cell_id, category_id, count) VALUES ('All Quiet on the Western Front', 2, 9, 4), ('Three Comrades', 2, 9, 1), ('Arch of Triumph', 2, 9, 3), ('The Black Obelisk', 2, 9, 2);
--Bulgakov
INSERT INTO book (name, cell_id, category_id, count) VALUES ('The Master and Margarita', 2, 9, 1), ('White Guard', 2, 9, 4), ('The Fatal Eggs', 2, 9, 7);
--Remender
INSERT INTO book (name, cell_id, category_id, count) VALUES ('Low', 3, 1, 3), ('Black science', 3, 1, 2), ('Tokyo ghost', 3, 1, 3);
--Vaughan
INSERT INTO book (name, cell_id, category_id, count) VALUES ('Saga', 3, 1, 5), ('We stand on guard', 3, 1, 4),('Private eye', 3, 1, 1);
--Kiedis
INSERT INTO book (name, cell_id, category_id, count) VALUES ('Scar Tissue', 4, 5, 2);
--Osbourne
INSERT INTO book (name, cell_id, category_id, count) VALUES ('I am Ozzy', 4, 5, 3);
--client
INSERT INTO client (first_name, last_name, phone) VALUES ('Vitaliy', 'Rybkin', '067464383'), ('Evgeniy', 'Fomin', '067455583'), ('Valeriy', 'Schevchenko', '067464888'), ('Aleksandra', 'Adusheva', '067444483'), ('Sergey', 'Kozlovskiy', '067786583');
--rent_journal
INSERT INTO rent_journal (book_id, client_id, rent_date, return_date) VALUES (1, 1, '2015-12-02', NULL), (3, 1, '2015-07-08', NULL), (5, 1, '2015-09-10', NULL), (7, 2, '2015-09-25', '2015-10-25'), (15, 3, '2015-04-05', NULL), (9, 4, '2015-09-13', NULL), (10, 4, '2015-08-13', NULL);
--role
INSERT INTO role (role_id, role_name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
--user
INSERT INTO user (user_id, user_name, user_password, role_id) VALUES (1, 'admin', 'admin', 1), (2, 'librarian', 'librarian', 2), (3, 'jax', 'jax', 1);
--author_book
INSERT INTO author_book (author_id, book_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (2, 6), (2, 7), (2, 8), (3, 9), (3, 10), (3, 11), (4, 12), (4, 13), (4, 14), (5, 15), (5, 16), (5, 17), (5, 18), (6, 19), (6, 20), (6, 21), (7, 22), (7, 23), (7, 24), (8, 25), (8, 26), (8, 27), (9, 28), (10, 29);