--author
INSERT INTO author (first_name,last_name) VALUES ('Alexandr','Pushkin');
INSERT INTO author (first_name,last_name) VALUES ('Nikolay','Gogol');
INSERT INTO author (first_name,last_name) VALUES ('John','Smith');
INSERT INTO author (first_name,last_name) VALUES ('Jeff','Johnson');
INSERT INTO author (first_name,last_name) VALUES ('Peter','Griffin');
INSERT INTO author (first_name,last_name) VALUES ('Stew','Griffin');
INSERT INTO author (first_name,last_name) VALUES ('Lila','Turanga');
INSERT INTO author (first_name,last_name) VALUES ('Brian','Griffin');
INSERT INTO author (first_name,last_name) VALUES ('Fry','First');
INSERT INTO author (first_name,last_name) VALUES ('Gomer','Simpson');
INSERT INTO author (first_name,last_name) VALUES ('TEST','TEST');
UPDATE author SET first_name = 'Vasya' WHERE last_name = 'TEST';
DELETE FROM author WHERE last_name = 'TEST';
--category
INSERT INTO category (name) VALUES ('horror');
INSERT INTO category (name) VALUES ('comedy');
INSERT INTO category (name) VALUES ('detective');
INSERT INTO category (name) VALUES ('triller');
INSERT INTO category (name) VALUES ('novel');
INSERT INTO category (name) VALUES ('comics');
INSERT INTO category (name) VALUES ('short story');
UPDATE category SET name = 'thriller' where name = 'triller';
--cell
INSERT INTO cell (name) VALUES ('A1');
INSERT INTO cell (name) VALUES ('A2');
INSERT INTO cell (name) VALUES ('A3');
INSERT INTO cell (name) VALUES ('A4');
INSERT INTO cell (name) VALUES ('A5');
INSERT INTO cell (name) VALUES ('A6');
INSERT INTO cell (name) VALUES ('A7');
INSERT INTO cell (name) VALUES ('B1');
INSERT INTO cell (name) VALUES ('B2');
INSERT INTO cell (name) VALUES ('B3');
--book
INSERT INTO book (name,cell_id,category_id) VALUES ('JAVA for dummies','1','1');
INSERT INTO book (name,cell_id,category_id) VALUES ('JAVA for dummies 2 Return','2','7');
INSERT INTO book (name,cell_id,category_id) VALUES ('Poems','1','5');
INSERT INTO book (name,cell_id,category_id) VALUES ('Romantic poems','3','1');
INSERT INTO book (name,cell_id,category_id) VALUES ('Nos','4','3');
INSERT INTO book (name,cell_id,category_id) VALUES ('Report','5','2');
INSERT INTO book (name,cell_id,category_id) VALUES ('Jmeter manual','10','4');
INSERT INTO book (name,cell_id,category_id) VALUES ('H2 Best practice','1','3');
--author_book
INSERT INTO author_book VALUES ('10','8');
INSERT INTO author_book VALUES ('9','8');
INSERT INTO author_book VALUES ('7','8');
INSERT INTO author_book VALUES ('10','6');
INSERT INTO author_book VALUES ('10','5');
INSERT INTO author_book VALUES ('1','1');
INSERT INTO author_book VALUES ('2','4');
--client
INSERT INTO client (first_name,last_name,phone,email) VALUES ('Petya','Vasechkin','777-3-555','petya@mail.com');
INSERT INTO client (first_name,last_name,phone,email) VALUES ('Vasya','Petechkin','777-3-556','vasya@mail.com');
INSERT INTO client (first_name,last_name,phone) VALUES ('Neo','Ivanov','777-3-558');
INSERT INTO client (first_name,last_name,phone) VALUES ('Ivan','Petrov','788-3-000');
INSERT INTO client (first_name,last_name,phone) VALUES ('Alexandr','Rex','777-3-558');
INSERT INTO client (first_name,last_name,email) VALUES ('John','Ivanov','john@mail.com');
--rent_journal
INSERT INTO rent_journal (book_id,client_id,rent_date,expired_date,return_date) VALUES ('1','1','2015-11-10','2015-11-15','2015-11-15');
INSERT INTO rent_journal (book_id,client_id,rent_date,expired_date,return_date) VALUES ('2','1','2015-12-10','2015-12-15','2015-12-15');
INSERT INTO rent_journal (book_id,client_id,rent_date,expired_date) VALUES ('3','2','2015-12-15','2015-12-20');
INSERT INTO rent_journal (book_id,client_id,rent_date,expired_date) VALUES ('4','3','2015-12-15','2015-12-20');