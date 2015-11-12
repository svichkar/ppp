
INSERT INTO category (name) VALUES ('Development');
INSERT INTO category (name) VALUES ('Building');
INSERT INTO category (name) VALUES ('Medicine');

INSERT INTO book (name,author,publisher,category_id) VALUES ('Thinking JAVA','Eckel','Ranok',1);
INSERT INTO book (name,author,publisher,category_id) VALUES ('Refactoring','Shildt','Vox',1);
INSERT INTO book (name,author,publisher,category_id) VALUES ('Wooden house','Petrovsy','buildLib',2);
INSERT INTO book (name,author,publisher,category_id) VALUES ('Melting','Skoroshev','Resorce',2);
INSERT INTO book (name,author,publisher,category_id) VALUES ('Liver diesases','Nekiforov','medLab',3);
INSERT INTO book (name,author,publisher,category_id) VALUES ('Surgery','Agapov','Prosvit',3);
DELETE FROM book WHERE author = 'Agapov';


INSERT INTO book_instance (book_id, inventory_number) VALUES (1,001);
INSERT INTO book_instance (book_id, inventory_number) VALUES (2,002);
INSERT INTO book_instance (book_id, inventory_number) VALUES (3,003);


INSERT INTO reader (name,adress) VALUES ('Nikolai Smirnov','Sumskays strt 3 app 12');
INSERT INTO reader (name,adress) VALUES ('Anna Karenina','Pravdy 4 app 23');
INSERT INTO reader (name,adress) VALUES ('Jack Bolotoff','Sumskays strt 32 app 122');
UPDATE reader SET name = 'Jack Bolotof' WHERE name = 'Jack Bolotoff';

INSERT INTO journal (id, book_id, reader_id, start_date, end_date) VALUES (1, 1, 1,parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('17-09-2016 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'))
INSERT INTO journal (id, book_id, eader_id, start_date, end_date) VALUES (2, 2, 2,parsedatetime('13-10-2011 14:23:32.54', 'dd-MM-yyyy hh:mm:ss.SS'), parsedatetime('13-10-2019 14:23:32.54', 'dd-MM-yyyy hh:mm:ss.SS'))