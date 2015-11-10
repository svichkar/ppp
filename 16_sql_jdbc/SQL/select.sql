SELECT * FROM subject;

SELECT DISTINCT first_name FROM student;

SELECT last_name, date_entry FROM student;

SELECT * FROM student WHERE date_entry>'2005-10-01';

SELECT * FROM journal WHERE rate IN('5', '4');

SELECT * FROM student WHERE status_id = '1';

SELECT * FROM student WHERE date_birthday BETWEEN '1990-01-01' AND '1991-12-31';

SELECT * FROM student WHERE first_name LIKE 'Alex%';

SELECT * FROM student WHERE status_id = (SELECT status_id FROM status WHERE status_name='active');

SELECT * FROM student ORDER BY first_name;

SELECT * FROM student WHERE student_group_id = '1' UNION SELECT * FROM student WHERE status_id = '1';

SELECT first_name, last_name, status_name, group_name FROM student stud INNER JOIN status stat ON stud.status_id = stat.status_id INNER JOIN student_group sg ON stud.student_group_id = sg.student_group_id;

SELECT * FROM subject sub INNER JOIN term t ON sub.term_id = t.term_id;

SELECT * FROM student st LEFT OUTER JOIN student_group sg ON st.student_group_id = sg.student_group_id;

SELECT * FROM student st RIGHT OUTER JOIN student_group sg ON st.student_group_id = sg.student_group_id;

SELECT * FROM student WHERE status_id = ROUND(1.5);

SELECT * FROM student WHERE CONCAT(first_name, ' ',last_name) = 'Alexandr Ivanov';

SELECT * FROM student WHERE YEAR(date_birthday) = '1991';

SELECT group_name, COUNT (student_id) as number_of_students FROM student_group sg LEFT OUTER JOIN student s ON sg.student_group_id = s.student_group_id GROUP BY group_name HAVING COUNT (student_id) >=1;
