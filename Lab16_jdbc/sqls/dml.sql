INSERT INTO student_group (student_group) VALUES ('KSM-02-1');
INSERT INTO student_group (student_group) VALUES ('KSM-02-2');
INSERT INTO student_group (student_group) VALUES ('KSM-02-3');

INSERT INTO term (alias) VALUES ('I');
INSERT INTO term (alias) VALUES ('II');

INSERT INTO status (status_name) VALUES ('gap year');
INSERT INTO status (status_name) VALUES ('expelled');
INSERT INTO status (status_name) VALUES ('active');
UPDATE status 
SET status_name = 'enrolled'
WHERE status_name  = 'active';

INSERT INTO subject (name, term_id) VALUES ('History of Ukraine', 1);
INSERT INTO subject (name, term_id) VALUES ('Higher Mathematics', 1);
INSERT INTO subject (name, term_id) VALUES ('Philosophy', 1);
INSERT INTO subject (name, term_id) VALUES ('Physics', 1);
INSERT INTO subject (name, term_id) VALUES ('Analytic geometry', 2);
INSERT INTO subject (name, term_id) VALUES ('Boolean algebra', 2);
INSERT INTO subject (name, term_id) VALUES ('Chemistry', 2);
DELETE FROM subject
WHERE term_id = 2;
INSERT INTO subject (name, term_id) VALUES ('Analytic geometry', 2);
INSERT INTO subject (name, term_id) VALUES ('Boolean algebra', 2);

INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id)
VALUES ('Ivan', 'Peprov', '1998-02-01', '2015-07-12', 1, 1, 3);
INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id)
VALUES ('Ivan', 'Sidorov', '1997-03-05', '2015-08-01', 1, 1, 3);
INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id)
VALUES ('Ivan', 'Sidorov', '1998-05-15', '2015-07-28', 2, 1, 3);
INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id)
VALUES ('Pavel', 'Ivanov', '1998-09-25', '2015-07-30', 2, 1, 3);
INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id)
VALUES ('Vasiliy', 'Smirnov', '1997-01-14', '2014-07-01', 3, 2, 1);
INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id)
VALUES ('Anna', 'Bulba', '1997-12-03', '2015-07-10', 3, 2, 3);
UPDATE student 
SET status_id = '2'
WHERE first_name  = 'Pavel' AND last_name = 'Ivanov';

INSERT INTO journal (student_id, subject_id, rate) VALUES (1, 1, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (1, 2, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (1, 3, 'B');
INSERT INTO journal (student_id, subject_id, rate) VALUES (1, 4, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (2, 1, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (2, 2, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (2, 3, 'B');
INSERT INTO journal (student_id, subject_id, rate) VALUES (2, 4, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (3, 1, 'B');
INSERT INTO journal (student_id, subject_id, rate) VALUES (3, 2, 'C');
INSERT INTO journal (student_id, subject_id, rate) VALUES (3, 3, 'C');
INSERT INTO journal (student_id, subject_id, rate) VALUES (3, 4, 'E');
INSERT INTO journal (student_id, subject_id, rate) VALUES (4, 1, 'B');
INSERT INTO journal (student_id, subject_id, rate) VALUES (4, 2, 'C');
INSERT INTO journal (student_id, subject_id, rate) VALUES (4, 3, 'C');
INSERT INTO journal (student_id, subject_id, rate) VALUES (4, 4, 'A');
INSERT INTO journal (student_id, subject_id, rate) VALUES (5, 1, 'B');
INSERT INTO journal (student_id, subject_id, rate) VALUES (5, 2, 'C');
INSERT INTO journal (student_id, subject_id, rate) VALUES (5, 3, 'C');
INSERT INTO journal (student_id, subject_id, rate) VALUES (5, 4, 'B');
INSERT INTO journal (student_id, subject_id, rate) VALUES (6, 1, '');
INSERT INTO journal (student_id, subject_id, rate) VALUES (6, 2, '');
INSERT INTO journal (student_id, subject_id, rate) VALUES (6, 3, '');
INSERT INTO journal (student_id, subject_id, rate) VALUES (6, 4, '');