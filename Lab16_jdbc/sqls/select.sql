--5
SELECT * FROM journal;
SELECT name FROM subject;
SELECT first_name, last_name, date_birthday FROM student;
SELECT DISTINCT first_name, last_name FROM student;
--6
SELECT first_name, last_name, student_group_id FROM student
WHERE last_name LIKE ('%ov');
SELECT first_name, last_name, date_birthday FROM student
WHERE date_birthday > '1998-01-01';
SELECT first_name, last_name, student_group_id FROM student
WHERE student_group_id IN (1, 3);
SELECT first_name, last_name, student_group_id FROM student
WHERE student_group_id = 2;
SELECT * FROM student_group
WHERE student_group = 'KSM-02-2';
SELECT * FROM journal
WHERE rate IN('A', 'B');
SELECT * FROM student
WHERE date_entry BETWEEN '2015-07-1' AND '2015-07-31';
SELECT name FROM subject
WHERE term_id = 2;
--7
SELECT * FROM student
WHERE status_id = (SELECT status_id FROM status WHERE status_name = 'expelled');
SELECT first_name, last_name, student_group_id FROM student
WHERE student_group_id IN (SELECT student_group_id FROM student_group WHERE student_group IN('KSM-02-1', 'KSM-02-3'));
SELECT rate FROM journal
WHERE student_id = (SELECT student_id FROM student WHERE first_name = 'Vasiliy' AND last_name = 'Smirnov');
--8
SELECT * FROM student ORDER BY last_name ASC;
SELECT * FROM subject ORDER BY name DESC;
--9
SELECT * FROM student WHERE student_group_id = 1
UNION
SELECT * FROM student WHERE first_name = 'Ivan';
--10
SELECT s.first_name, s.last_name, st.status_name
FROM student s
INNER JOIN status st
ON s.status_id = st.status_id;
SELECT s.first_name, s.last_name, st.status_name, sg.student_group
FROM student s
INNER JOIN status st
ON s.status_id = st.status_id
INNER JOIN student_group sg
ON s.student_group_id = sg.student_group_id;
--11
SELECT sb.name, t.alias 
FROM subject sb
LEFT OUTER JOIN term t
ON sb.term_id = t.term_id;
SELECT sb.name, j.rate
FROM subject sb
LEFT OUTER JOIN journal j
ON sb.subject_id = j.subject_id;
--12
--is no suitable task (((
--13
SELECT LOWER(student_group) FROM student_group;
SELECT CONCAT('Name: ', first_name), CONCAT ('Surname: ', last_name) FROM student;
--14
SELECT first_name, last_name, DAYNAME(date_birthday) FROM student;
SELECT first_name, last_name, MONTHNAME(date_birthday) FROM student;
SELECT first_name, last_name, date_birthday FROM student
WHERE DAYNAME(date_birthday) = 'Friday';
--15
SELECT COUNT(name), term_id FROM subject GROUP BY term_id HAVING COUNT(name) > 3;
SELECT first_name, last_name FROM student 
GROUP BY last_name HAVING last_name LIKE ('%ov');