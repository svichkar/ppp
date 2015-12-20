--task #5
SELECT * FROM student; --returns all students information
SELECT group_name FROM student_group; --returns all group names 
SELECT subject_id, subject_name FROM subject; --returns subject id and names
SELECT DISTINCT first_name FROM student; --returns unique students names
--task #6
SELECT student_id, subject_id, grade_id  FROM journal
WHERE grade_id > 3; --returns student grades for students whose marks are greater than '3'
SELECT * FROM student
WHERE last_name LIKE 'B%'; --returns student info for students whose last names starting with 'B'
SELECT student_id, subject_id, grade_id FROM journal
WHERE student_id IN (1,2,3,4,5) AND grade_id IS NOT NULL; --returns not empty grades for specified 5 students
SELECT first_name, last_name, group_id FROM student
WHERE admission_date BETWEEN '2015-06-01' AND '2015-08-31'; --returns guys for summer days 
SELECT student_id, subject_id FROM journal
WHERE grade_id IS NULL; -- returns student_id and subject_id without grade
SELECT group_name FROM student_group
WHERE group_id IN (1,3,5,7,9); --returns groups with odd group ids
SELECT first_name, last_name FROM student
WHERE status_id <> 3; --returns names and surnames of all students except expelled ones (status_id=3)
SELECT first_name, last_name FROM student
WHERE term_id = 1 AND admission_date >= '2015-09-01'; --returns students for the first term added after Sept,01,2015
--task #7
SELECT first_name, last_name FROM student
WHERE student_id IN (SELECT DISTINCT student_id FROM journal
							WHERE grade_id > 3); --returns students whose marks are greater than '3'

SELECT student_id, subject_id, grade_id  FROM journal
WHERE subject_id IN (SELECT DISTINCT subject_id 
						    FROM subject WHERE term_id = 2); --returns journal info for second term subjects 
--task #8
SELECT student_id, subject_id, grade_id FROM journal
WHERE grade_id IS NOT NULL ORDER BY grade_id DESC; --returns journal info ordered by grade_id descending
--task #9 (TODO)
SELECT first_name, last_name FROM student
WHERE group_id
 
UNION

SELECT first_name, last_name FROM student
WHERE 
--task #10 (TODO)
--task #11
--task #12
SELECT student_id, subject_id, ROUND  ((grade_id/5.0)*100)
FROM journal;
--task #13



CREATE TABLE student_group(group_id BIGINT PRIMARY KEY, group_name VARCHAR(256) NOT NULL);
CREATE TABLE status (status_id TINYINT PRIMARY KEY, status_name VARCHAR(256) NOT NULL);
CREATE TABLE term(term_id BIGINT PRIMARY KEY);
CREATE TABLE student(student_id BIGINT IDENTITY PRIMARY KEY,
                 first_name VARCHAR(256) NOT NULL,
                 last_name VARCHAR(256) NOT NULL,
                 admission_date DATE,
                 group_id BIGINT NOT NULL REFERENCES student_group(group_id),
                 status_id TINYINT NOT NULL REFERENCES status(status_id),
                 term_id BIGINT REFERENCES term(term_id)
);
CREATE TABLE grade(grade_id TINYINT PRIMARY KEY, grade_name VARCHAR(256) NOT NULL);
CREATE TABLE subject(subject_id BIGINT PRIMARY KEY, 
                  subject_name VARCHAR(256) NOT NULL,
                  term_id BIGINT REFERENCES term(term_id));
CREATE TABLE journal(journal_id BIGINT IDENTITY PRIMARY KEY, 
                  student_id BIGINT REFERENCES student(student_id),
                  subject_id BIGINT REFERENCES subject(subject_id),
                  grade_id TINYINT REFERENCES grade(grade_id));