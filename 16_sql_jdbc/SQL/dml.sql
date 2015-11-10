INSERT INTO term (TERM_ALIAS) 
VALUES ('I');

INSERT INTO student_group (group_name) 
VALUES ('310');

INSERT INTO student_group (group_name) 
VALUES ('311');

INSERT INTO student_group (group_name) 
VALUES ('312');

INSERT INTO student_group (group_name) 
VALUES ('313');

INSERT INTO student_group (group_name) 
VALUES ('316');

DELETE FROM student_group
WHERE group_name = '312';

INSERT INTO status (status_name) 
VALUES ('active');

INSERT INTO status (status_name) 
VALUES ('inactive');

INSERT INTO subject (subject_name, term_id) 
VALUES ('Math','1');

INSERT INTO subject (subject_name, term_id) 
VALUES ('Physics','1');

INSERT INTO subject (subject_name, term_id) 
VALUES ('English','1');

INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id) 
VALUES ('Alex', 'Ivanov', '1990-01-01', '2007-09-01', '1', '1', '1');

INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id) 
VALUES ('Petr', 'Petrov', '1991-05-07', '2008-09-01', '1', '1', '1');

INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id) 
VALUES ('Victor', 'Sergeev', '1985-09-17', '2005-09-01', '2', '1', '2');

INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id) 
VALUES ('Victor', 'Lastochkin', '1991-01-02', '2007-09-01', '2', '1', '1');

INSERT INTO student (first_name, last_name, date_birthday, date_entry, student_group_id, term_id, status_id) 
VALUES ('Sergey', 'Korobov', '1991-010-05', '2007-09-01', '4', '1', '1');

UPDATE student
SET first_name = 'Alexandr'
WHERE student_id = '2';

INSERT INTO journal (student_id, subject_id, rate)
VALUES ('2', '1', '5');

INSERT INTO journal (student_id, subject_id, rate)
VALUES ('2', '2', '4');

INSERT INTO journal (student_id, subject_id, rate)
VALUES ('3', '1', '4');

INSERT INTO journal (student_id, subject_id, rate)
VALUES ('3', '3', '3');
