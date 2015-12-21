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
--task #9
SELECT first_name, last_name FROM student
WHERE group_id
 
UNION

SELECT first_name, last_name FROM student
WHERE 
--task #10
SELECT first_name, last_name, group_name FROM student st
INNER JOIN student_group sg ON (st.group_id = sg.group_id);


--task #11
SELECT subject_id, subject_name, term_name FROM subject s
LEFT OUTER JOIN term t ON (s.term_id = t.term_id); --returns all subjects with term name
SELECT first_name, last_name, subject_id, grade_id FROM student st
RIGHT OUTER JOIN journal j ON (st.student_id = j.student_id)
WHERE grade_id IS NOT NULL AND status_id <> 3
ORDER BY subject_id; --returns list of students with grades
SELECT first_name, last_name, group_name, term_id, status_name FROM student stud
LEFT OUTER JOIN student_group sg ON (stud.group_id = sg.group_id)
LEFT OUTER JOIN status st ON (stud.status_id = st.status_id)
ORDER BY group_name, term_id; --returns readable student info with full names
--task #12
SELECT student_id, subject_id, ROUND (grade_id/5.0)*100)
FROM journal;--returns one more metric for grade values
--task #13
SELECT TRIM(UPPER(first_name)), TRIM(UPPER(last_name)) FROM student
WHERE admission_date > '2015-06-01'; --returns uppercase student names and surnames
--task #14
SELECT first_name, last_name, admission_date, QUARTER(admission_date) from student
WHERE status_id <> 3;-- added column with quaters for admission date values
--task #15
SELECT group_id, COUNT(student_id) FROM student
WHERE status_id = 1 GROUP BY group_id; --returns number of active students for each group
SELECT student_id, AVG(grade_id) FROM journal
GROUP BY student_id
HAVING AVG(grade_id) >= 4; --returns students and their evarage grades for studens having grades greater or equals 4