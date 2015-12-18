INSERT INTO student_group
VALUES (1, 'KI-2015');

UPDATE student_group SET group_name='KI-2014' WHERE group_id=1;

INSERT INTO student_group
VALUES (2, 'PI-2015');

INSERT INTO student_group
VALUES (3, 'KI-2015');

INSERT INTO student_group
VALUES (4, 'KI-2016');

DELETE FROM student_group WHERE group_name='KI-2016';

INSERT INTO status
VALUES (1, 'Active');

INSERT INTO status
VALUES (2, 'Academic leave');

INSERT INTO status
VALUES (3, 'Expelled');

INSERT INTO term
VALUES (1, 'Autumn-2014');

INSERT INTO term
VALUES (2, 'Spring-2015');

INSERT INTO term
VALUES (3, 'Autumn-2015');

INSERT INTO term
VALUES (4, 'Spring-2016');

INSERT INTO subject
VALUES (1, 'Mathematics', 1);

INSERT INTO subject
VALUES (2, 'Engineering', 1);

INSERT INTO subject
VALUES (3, 'Ethics', 1);

INSERT INTO subject
VALUES (4, 'Philosophy', 2);

INSERT INTO subject
VALUES (5, 'Information Systems', 2);

INSERT INTO subject
VALUES (6, 'Software Engineering', 2);

INSERT INTO subject
VALUES (7, 'Statistics', 3);

INSERT INTO subject
VALUES (8, 'Computer Science', 3);

INSERT INTO subject
VALUES (9, 'Electronic Engineering', 3);

UPDATE subject SET subject_name='Electrical and Electronic Engineering' WHERE subject_id=9;

INSERT INTO subject
VALUES (10, 'Psycology', 3);

DELETE FROM subject WHERE subject_name='Psycology';

INSERT INTO grade
VALUES (1, 'Fail');

INSERT INTO grade
VALUES (2, 'Unsatisfactory');

INSERT INTO grade
VALUES (3, 'Satisfactory');

INSERT INTO grade
VALUES (4, 'Good');

INSERT INTO grade
VALUES (5, 'Excellent');

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('John', 'Smith', 1, '2014-09-01', 1, 1);

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('Tom', 'Foster', 1, '2014-09-01', 2, 1);

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('Kate', 'McLain', 1, '2014-09-01', 3, 1);

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('Julie', 'West', 2, '2014-09-01', 1, 2);

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('Jane', 'Reid', 2, '2014-09-01', 1, 2);

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('Craig', 'Cambell', 3, '2015-09-01', 1, 3);

INSERT INTO student (first_name, last_name, group_id, admission_date, status_id, term_id)
VALUES ('Steve', 'Edwards', 3, '2015-09-01', 1, 3);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (1, 1, 5), (1, 2, 5), (1, 3, 4);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (2, 1, 4), (2, 2, 5), (2, 3, 4);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (3, 1, 2), (3, 2, 1), (3, 3, 1);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (4, 4, 5), (4, 5, 4), (4, 6, 3);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (5, 4, 4), (5, 5, 4), (5, 6, 5);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (6, 7, 4), (6, 8, 3), (6, 9, 4);

INSERT INTO journal (student_id, subject_id, grade_id)
VALUES (7, 7, 5), (7, 8, 5), (7, 9, 5);
