------------------------------------------------------------------------
--task #5
------------------------------------------------------------------------
--returns all students information:
SELECT * FROM student; 
--returns all group names: 
SELECT group_name FROM student_group;
--returns subject id and names:
SELECT subject_id, subject_name FROM subject;
--returns unique students names:
SELECT DISTINCT first_name FROM student; 
------------------------------------------------------------------------
--task #6
------------------------------------------------------------------------
--returns student grades for students whose marks are greater than '3':
SELECT student_id, subject_id, grade_id  FROM journal
WHERE grade_id > 3; 
--returns student info for students whose last names starting with 'B':
SELECT * FROM student
WHERE last_name LIKE 'B%'; 
--returns not empty grades for specified 5 students:
SELECT student_id, subject_id, grade_id FROM journal
WHERE student_id IN (1,2,3,4,5) AND grade_id IS NOT NULL; 
--returns guys for summer days:
SELECT first_name, last_name, group_id FROM student
WHERE admission_date BETWEEN '2015-06-01' AND '2015-08-31';  
-- returns student_id and subject_id without grade:
SELECT student_id, subject_id FROM journal
WHERE grade_id IS NULL;
--returns groups with odd group ids:
SELECT group_name FROM student_group
WHERE group_id IN (1,3,5,7,9); 
--returns names and surnames of all students except expelled ones (status_id=3):
SELECT first_name, last_name FROM student
WHERE status_id <> 3;
--returns students for the first term added after Sept,01,2015: 
SELECT first_name, last_name FROM student
WHERE term_id = 1 AND admission_date >= '2015-09-01';
------------------------------------------------------------------------
--task #7
------------------------------------------------------------------------
--returns students whose marks are greater than '3':
SELECT first_name, last_name FROM student
WHERE student_id IN (SELECT DISTINCT student_id FROM journal
							WHERE grade_id > 3); 
--returns journal info for second term subjects:
SELECT student_id, subject_id, grade_id  FROM journal
WHERE subject_id IN (SELECT DISTINCT subject_id 
						    FROM subject WHERE term_id = 2);  
------------------------------------------------------------------------
--task #8
------------------------------------------------------------------------
--returns journal info ordered by grade_id descending:
SELECT student_id, subject_id, grade_id FROM journal
WHERE grade_id IS NOT NULL ORDER BY grade_id DESC; 
------------------------------------------------------------------------
--task #9
------------------------------------------------------------------------
-- returns students studding Java plus all not expelled and not graduated:
SELECT first_name, last_name FROM student 
WHERE term_id = (SELECT term_id FROM subject
                                  WHERE subject_id = 1)
UNION
SELECT first_name, last_name FROM student stud
INNER JOIN status stat ON (stud.status_id = stat.status_id)
WHERE NOT status_name IN ( 'expelled', 'graduated');
------------------------------------------------------------------------
--task #10
------------------------------------------------------------------------
--returns not empty groups with student names and surnames:
SELECT first_name, last_name, group_name FROM student st
INNER JOIN student_group sg ON (st.group_id = sg.group_id); 
--returns students having not empty grades in journal:
SELECT first_name, last_name, j.grade_id, grade_name FROM student st
INNER JOIN journal j ON (st.student_id = j.student_id)
INNER JOIN grade g ON (g.grade_id = j.grade_id ); 
------------------------------------------------------------------------
--task #11
------------------------------------------------------------------------
--returns all subjects with term name:
SELECT subject_id, subject_name, term_name FROM subject s
LEFT OUTER JOIN term t ON (s.term_id = t.term_id); 
--returns list of students with grades:
SELECT first_name, last_name, subject_id, grade_id FROM student st
RIGHT OUTER JOIN journal j ON (st.student_id = j.student_id)
WHERE grade_id IS NOT NULL AND status_id <> 3
ORDER BY subject_id; 
--returns readable student info with full names:
SELECT first_name, last_name, group_name, term_id, status_name FROM student stud
LEFT OUTER JOIN student_group sg ON (stud.group_id = sg.group_id)
LEFT OUTER JOIN status st ON (stud.status_id = st.status_id)
ORDER BY group_name, term_id; 
------------------------------------------------------------------------
--task #12
------------------------------------------------------------------------
--returns one more metric for grade values:
SELECT student_id, subject_id, ROUND ((grade_id/5.0)*100)
FROM journal WHERE grade_id IS NOT NULL;
------------------------------------------------------------------------
--task #13
------------------------------------------------------------------------
--returns upper-case student names and surnames:
SELECT TRIM(UPPER(first_name)), TRIM(UPPER(last_name)) FROM student
WHERE admission_date > '2015-06-01'; --returns upper-case student names and surnames
------------------------------------------------------------------------
--task #14
------------------------------------------------------------------------
--added column with quarters for admission date values:
SELECT first_name, last_name, admission_date, QUARTER(admission_date) from student
WHERE status_id <> 3;
------------------------------------------------------------------------
--task #15
------------------------------------------------------------------------
--returns number of active students for each group:
SELECT group_id, COUNT(student_id) FROM student
WHERE status_id = 1 GROUP BY group_id; 
--returns students and their average grades for students having grades greater or equals 4:
SELECT student_id, AVG(grade_id) FROM journal
GROUP BY student_id
HAVING AVG(grade_id) >= 4; 