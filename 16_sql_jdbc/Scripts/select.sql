--5
SELECT * FROM worker;
SELECT * FROM part;
SELECT * FROM car;
SELECT order_id, description, order_status_id FROM order_in_work;
SELECT part_id, amount FROM part;
SELECT first_name, last_name FROM customer;
SELECT DISTINCT worker_id FROM order_worker;
SELECT DISTINCT part_id FROM order_part;
SELECT DISTINCT status_id FROM worker_status;
--6
SELECT * FROM worker WHERE first_name like ('Jo%');
SELECT * FROM part WHERE part_name like ('%gear%');
SELECT * FROM worker WHERE specialization_id >= 4;
SELECT * FROM part WHERE amount > 3;
SELECT * FROM car WHERE description IN ('Green', 'Red');
SELECT * FROM customer WHERE first_name IN ('John', 'Selesta');
SELECT * FROM part WHERE amount BETWEEN 0 AND 8;
SELECT * FROM worker WHERE worker_id BETWEEN 1 AND 3;
SELECT * FROM order_in_work WHERE timestamp_finish IS NULL;
SELECT * FROM car WHERE description IS NOT NULL;
--7
SELECT * FROM part WHERE part_id IN (SELECT part_id FROM order_part);
SELECT * FROM worker WHERE specialization_id IN (SELECT specialization_id FROM worker_specialization WHERE specialization_name = 'Worker');
SELECT * FROM worker_status WHERE worker_id IN (SELECT worker_id FROM worker WHERE specialization_id IN (SELECT specialization_id FROM worker_specialization WHERE specialization_name = 'Trainee'));
--8
SELECT * FROM car ORDER BY model ASC;
SELECT * FROM part ORDER BY amount DESC;
--9
SELECT * FROM worker WHERE first_name = 'John'
UNION
SELECT * FROM worker WHERE last_name = 'Face';
--10
SELECT w.*, s.status_name FROM worker w
INNER JOIN worker_status ws ON w.worker_id = ws.worker_id
INNER JOIN status s ON ws.status_id = s.status_id;
SELECT o.*, p.part_name, op.used_amount FROM order_in_work o
INNER JOIN order_part op ON o.order_id = op.order_id
INNER JOIN part p ON op.part_id = p.part_id;
--11
SELECT p.*, op.used_amount FROM part p
LEFT JOIN order_part op ON p.part_id = op.part_id;
SELECT w.*, ow.order_id FROM worker w
LEFT JOIN order_worker ow ON w.worker_id = ow.worker_id;
--12
SELECT MOD(amount, 5) FROM part WHERE part_name = 'Small gear';
--13
SELECT * FROM worker WHERE LENGTH(REPLACE(first_name, 'John', '')) = 0;
SELECT part_id, SUBSTRING(part_name, 0, 4) FROM part;
--14
SELECT * FROM order_in_work WHERE MONTHNAME(timestamp_start) = 'November';
SELECT * FROM order_in_work WHERE timestamp_start > PARSEDATETIME('01-11-2015', 'dd-MM-yyyy');
SELECT * FROM order_in_work WHERE timestamp_start BETWEEN PARSEDATETIME('2015-11-05 20:02:38.614', 'yyyy-MM-dd HH:mm:ss.SS') AND PARSEDATETIME('2015-11-05 20:02:38.615', 'yyyy-MM-dd HH:mm:ss.SS');
--15
SELECT cu.first_name, cu.last_name, COUNT(c.car_id) FROM customer cu
INNER JOIN car c ON cu.customer_id = c.customer_id
GROUP BY first_name, last_name HAVING COUNT(c.car_id) > 1;
SELECT part_name, amount FROM part WHERE amount = (SELECT MAX(amount) FROM part);
SELECT p.part_name, SUM(op.used_amount) FROM part p
INNER JOIN order_part op ON p.part_id = op.part_id
GROUP BY p.part_name;