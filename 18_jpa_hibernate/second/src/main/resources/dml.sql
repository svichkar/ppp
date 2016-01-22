--Fill 'role' table:
INSERT INTO role (role_id, role_name) VALUES (1,'admin'), (2, 'manager');

--Fill 'user' table:
INSERT INTO user (first_name, last_name, email, login, password, role_id) VALUES ('Konstantin', 'Svichkar', 'admin@gmail.com', 'svichkar', '123456', 1);
INSERT INTO user (first_name, last_name, email, login, password, role_id) VALUES ('Guest', 'Guestovich', 'guest@gmail.com', 'guest', '54321', 2);
INSERT INTO user (first_name, last_name, email, login, password, role_id) VALUES ('Manager', 'First', 'one@gmail.com', 'manager', 'manager', 2);
INSERT INTO user (first_name, last_name, email, login, password, role_id) VALUES ('Admin', 'Second', 'admin2@gmail.com', 'admin', 'admin', 1);

--Fill 'grade' table:
INSERT INTO grade VALUES (1, 'poor'), (2, 'fair'), (3, 'average'), (4, 'good'), (5, 'excellent');

--Fill 'term' table:
INSERT INTO term(term_name) VALUES ('first'), ('second'), ('third'), ('fourth'), ('fifth');

--Fill 'subject' table:
INSERT INTO subject (subject_name, term_id) VALUES ('Java', 2), ('Mathematics', 1),('Physics', 1),('Computing science', 2),('Telecommunications', 1);
INSERT INTO subject (subject_name, term_id) VALUES ('Databases', 2), ('Security', 1),('Web technologies', 2),('Chemistry', 1),('Theory of everything', 1);

--Fill 'status' table:
INSERT INTO status(status_name) VALUES ('active'), ('academic vacation'), ('expelled'), ('graduated'), ('inactive');

--Fill 'student_group' table:
INSERT INTO student_group (group_name) VALUES ('java 15-1'), ('java 15-2'),('java 15-3'),('java 15-4'),('java 15-5');
INSERT INTO student_group (group_name) VALUES ('java 15-6'),('java 15-7'),('java 15-8'),('java 15-9'),('java 15-10');

--Fill 'student' table:
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Alex', 'Ross','2015-03-25', 1, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Nick', 'Dodson','2015-07-03', 2, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Andrew', 'Galvan','2015-05-10', 3, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Jordan', 'Lynn','2015-10-22', 1, 3, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Marlon', 'Bullock','2015-03-11', 2, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Yosef', 'Mcmillan','2015-04-07', 3, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Mary', 'Kane','2015-06-02', 1, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Erica', 'Norman','2015-05-07', 2, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('August', 'Hill','2015-07-27', 3, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Nina', 'Coleman','2015-07-10', 1, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Rihanna', 'Nunez','2015-12-09', 2, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Jimmy', 'Bright','2015-02-03', 3, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Anna', 'Ferrell','2015-11-01', 1, 4, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Alex', 'Smith','2015-06-08', 2, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Sebastian', 'Oneal','2015-03-17', 3, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Natalie', 'Brock','2015-11-11', 1, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Lisa', 'Jefferson','2015-04-29', 2, 4, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Andrew', 'Norman','2015-12-11', 3, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Lincoln', 'Turner','2015-12-04', 1, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Alex', 'Ayers','2015-02-25', 2, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Alex', 'Ryan','2015-07-19', 3, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Robert', 'Nicholson','2015-06-13', 1, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Anna', 'Williams','2015-08-05', 2, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Natalie', 'Norman','2015-08-20', 3, 2, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Lance', 'Stokes','2015-03-16', 1, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Anna', 'Beltran','2015-01-21', 2, 4, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Alex', 'Yang','2015-06-05', 3, 1, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Natalie', 'Stevenson','2015-09-01', 1, 1, 2);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Dayton', 'Meyers','2015-05-04', 2, 2, 1);
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) VALUES ('Nelli', 'Rufus','2015-09-08', 3, 1, 2);

--Fill 'journal' table:
INSERT INTO journal(student_id, subject_id, grade_id) VALUES(14, 7, 3);