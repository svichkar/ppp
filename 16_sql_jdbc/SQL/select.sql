SELECT * FROM student_group;
SELECT first_name, last_name FROM student;
SELECT DISTINCT subject_id FROM journal;
SELECT first_name, last_name, admission_date FROM student WHERE admission_date>'2015-05-01';
SELECT * FROM student_group WHERE group_name LIKE 'KI%';
SELECT * FROM student_group WHERE group_name LIKE '%2014';
SELECT * FROM subject WHERE subject_name LIKE '%Engineering%';
SELECT * FROM term WHERE term_name LIKE 'Spring%';
SELECT student_id FROM journal WHERE grade_id IN(4,5);
SELECT first_name, last_name, admission_date FROM student WHERE admission_date BETWEEN '2014-05-01' AND '2015-05-01';
SELECT * FROM journal WHERE grade_id IS NULL;
SELECT first_name, last_name FROM student WHERE status_id IN (SELECT status_id FROM status WHERE status_name='Active');
SELECT subject_name FROM subject WHERE term_id IN (SELECT term_id FROM term WHERE term_name='Autumn-2015');
SELECT first_name, last_name FROM student WHERE group_id IN (SELECT group_id FROM student_group WHERE group_name LIKE 'KI%');
SELECT * FROM student ORDER BY admission_date;

SELECT first_name, last_name, status_id, admission_date
FROM student WHERE status_id IN (SELECT status_id FROM status WHERE status_name='Active')
UNION
SELECT first_name, last_name, status_id, admission_date
FROM student WHERE admission_date>'2015-05-01';

SELECT st.first_name, st.last_name, s.status_name
FROM student st JOIN status s ON (st.status_id=s.status_id);

--select student's grades on subjects
SELECT st.first_name, st.last_name, s.subject_name, g.grade_name
FROM student st JOIN journal j ON (st.student_id=j.student_id)
JOIN subject s ON (s.subject_id=j.subject_id)
JOIN grade g ON (g.grade_id=j.grade_id);

SELECT t.term_name, st.first_name, st.last_name
FROM term t LEFT JOIN student st ON (t.term_id=st.term_id);

SELECT t.term_name, s.subject_name 
FROM  subject s RIGHT JOIN term t ON (t.term_id=s.term_id);

--select random student
SELECT first_name, last_name FROM
(SELECT first_name, last_name, RAND() AS rand
FROM  student ORDER BY rand) WHERE rownum='1';

SELECT first_name, UPPER(last_name) FROM student;

SELECT first_name, last_name, YEAR(admission_date) as admission_year FROM student;

--select average grade for student
SELECT st.first_name, st.last_name, AVG(j.grade_id)
FROM student st JOIN journal j ON (st.student_id=j.student_id)
GROUP BY st.first_name, st.last_name;

--select groups with number of active students more than 1
SELECT g.group_name, COUNT(st.student_id) as number_of_active_students
FROM student st JOIN student_group g ON (st.group_id=g.group_id)
JOIN status s ON (st.status_id=s.status_id AND s.status_name='Active')
GROUP BY g.group_name
HAVING number_of_active_students>1;
