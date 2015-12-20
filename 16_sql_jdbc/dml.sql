--Fill 'grade' table:
INSERT INTO grade VALUES (1, 'poor'), (2, 'fair'), (3, 'average'), (4, 'good'), (5, 'excellent');
--Fill 'term' table:
INSERT INTO term(term_id, term_name) VALUES (1, 'first'), (2, 'second');
--Fill 'subject' table:
INSERT INTO subject (subject_id, subject_name, term_id) VALUES (1, 'Java', 2), 
					(2, 'Mathematics', 1),
					(3, 'Physics', 1),
					(4, 'Computing science', 2),
					(5, 'Telecomunications', 1),
					(6, 'Databases', 2),
					(7, 'Security', 1),
					(8, 'Web tehnologies', 2),
					(9, 'Chemistry', 1),
					(10, 'Theory of everything', 1);
					
--Fill 'status' table:
INSERT INTO status(status_id, status_name) VALUES (1, 'active'), (2, 'vacation'), (3, 'expelled'), (4, 'graduated');
--Update status name 'vacation' to 'academic vacation' 
UPDATE status SET status_name = 'academic vacation' WHERE status_id = 2;	

--Fill 'student_group' table:
INSERT INTO student_group VALUES (1, 'java 15-1'),
								 (2, 'java 15-2'), 
								 (3, 'java 15-3'), 
								 (4, 'java 15-4'), 
								 (5, 'java 15-5'), 
								 (6, 'java 15-6'), 
								 (7, 'java 15-7'), 
								 (8, 'java 15-8'), 
								 (9, 'java 15-9'),
								 (10, 'java 15-10'),
								 (11, 'others');
--Delete 'others' group 								 
DELETE FROM student_group WHERE group_name = 'others';

--Fill 'student' table:
INSERT INTO student(first_name, last_name, admission_date, group_id, status_id, term_id) 
VALUES  ('Alex', 'Ross','2015-03-25', 1, 1, 1),
		('Nick', 'Dodson','2015-07-03', 2, 1, 2),
		('Andrew', 'Galvan','2015-05-10', 3, 1, 1),
		('Jordan', 'Lynn','2015-10-22', 1, 3, 2),
		('Marlon', 'Bullock','2015-03-11', 2, 1, 1),
		('Yosef', 'Mcmillan','2015-04-07', 3, 1, 2),
		('Mary', 'Kane','2015-06-02', 1, 1, 1),
		('Erica', 'Norman','2015-05-07', 2, 1, 2),
		('August', 'Hill','2015-07-27', 3, 1, 1),
		('Nina', 'Coleman','2015-07-10', 1, 1, 2),
		('Rihanna', 'Nunez','2015-12-09', 2, 1, 1),
		('Jimmy', 'Bright','2015-02-03', 3, 1, 2),
		('Anna', 'Ferrell','2015-11-01', 1, 4, 1),
		('Alex', 'Smith','2015-06-08', 2, 1, 2),
		('Sebastian', 'Oneal','2015-03-17', 3, 1, 1),
		('Natalie', 'Brock','2015-11-11', 1, 1, 2),
		('Lisa', 'Jefferson','2015-04-29', 2, 4, 1),
		('Andrew', 'Norman','2015-12-11', 3, 1, 2),
		('Lincoln', 'Turner','2015-12-04', 1, 1, 1),
		('Alex', 'Ayers','2015-02-25', 2, 1, 2),
		('Alex', 'Ryan','2015-07-19', 3, 1, 1),
		('Robert', 'Nicholson','2015-06-13', 1, 1, 2),
		('Anna', 'Williams','2015-08-05', 2, 1, 1),
		('Natalie', 'Norman','2015-08-20', 3, 2, 2),
		('Lance', 'Stokes','2015-03-16', 1, 1, 1),
		('Anna', 'Beltran','2015-01-21', 2, 4, 2),
		('Alex', 'Yang','2015-06-05', 3, 1, 1),
		('Natalie', 'Stevenson','2015-09-01', 1, 1, 2),
		('Dayton', 'Meyers','2015-05-04', 2, 2, 1),
		('Bill', 'Gates','2015-01-01', 2, 1, 1),
		('Nelli', 'Rufus','2015-09-08', 3, 1, 2);
--Set status to 'expelled' (status_id=3) for specified user
UPDATE student SET status_id = 3 WHERE first_name = 'Rihanna' AND last_name = 'Nunez';
--Delete Bill Gates from 'java 15-2' group(group_id=2)
DELETE FROM student WHERE first_name = 'Bill' AND last_name = 'Gates' AND group_id = 2;						

--Fill 'journal' table:
INSERT INTO journal(student_id, subject_id, grade_id) 
  VALUES(14, 7, 3),(20, 3, NULL),(12, 2, NULL),
		(20, 10, 5),(7, 2, NULL),(13, 5, 4),
		(21, 6, 3),(17, 9, NULL),(4, 5, NULL),
		(3, 4, NULL),(3, 6, 4),(9, 9, 5),
		(10, 2, 3),(14, 2, 4),(9, 7, 2),
		(8, 4, 3),(18, 9, 3),(19, 2, 5),
		(5, 7, 3),(13, 3, 4),(20, 2, NULL),
		(26, 9, 5),(4, 8, 3),(26, 5, NULL),
		(26, 9, 4),(6, 3, NULL),(16, 9, 3),
		(16, 6, 5),(2, 10, 2),(2, 5, 5);