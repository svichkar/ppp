SELECT * FROM car;
SELECT car_id, start_date FROM car_order;
SELECT DISTINCT brand FROM car_type;

SELECT * FROM car WHERE CAR_ID > 10003;
SELECT * FROM car WHERE CAR_ID BETWEEN 10003 AND 10005;
SELECT * FROM car_order WHERE end_date IS NOT NULL;
SELECT * FROM car_type WHERE brand IN ('VAZ', 'Skoda');
SELECT * FROM car WHERE CAR_ID = 10003;
SELECT * FROM car WHERE CAR_ID != 10003;
SELECT * FROM car WHERE CAR_ID < 10003;

SELECT * FROM car WHERE car_type_id IN (SELECT car_type_id FROM car_type WHERE brand = 'VAZ');
SELECT serial_id FROM car WHERE client_id IN (SELECT client_id FROM client WHERE last_name = 'Jackson');
SELECT * FROM car_order WHERE car_order_status_id IN (SELECT car_order_status_id FROM car_order_status WHERE name = 'Complete');

SELECT * FROM car_type ORDER BY brand DESC;

SELECT * FROM client WHERE first_name = 'Homer'
UNION
SELECT * FROM client WHERE last_name = 'Jackson';

SELECT ct.brand, ct.model_name FROM car_type ct
INNER JOIN car c
ON c.car_type_id = ct.car_type_id;
SELECT emp.first_name, emp.last_name FROM employee emp
INNER JOIN employee_car_order emporder
ON emp.employee_id = emporder.employee_id;

SELECT * FROM car_type ct
LEFT OUTER JOIN car c
ON c.car_type_id = ct.car_type_id;
SELECT * FROM employee emp
RIGHT OUTER JOIN employee_car_order emporder
ON emp.employee_id = emporder.employee_id;

SELECT brand FROM car_type
ORDER BY RAND()
LIMIT 1;

SELECT SUBSTRING(model_name, 0, 4) FROM car_type;

SELECT * FROM car_order WHERE YEAR(start_date) = '2015';

SELECT brand, COUNT(model_name) FROM car_type WHERE brand = 'Skoda'
GROUP BY brand;

SELECT brand FROM car_type
GROUP BY brand HAVING COUNT(model_name) > 1;