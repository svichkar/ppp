--simple select
SELECT * FROM car_order;
SELECT car_id, start_date, end_date FROM car_order;
SELECT DISTINCT first_name, last_name FROM  client;

--simple conditions
SELECT car_id, start_date, end_date FROM car_order WHERE end date IS NOT NULL;
SELECT * FROM client WHERE last_name LIKE 'Groz%';
SELECT car_id FROM car_order WHERE start_date BETWEEN TIMESTAMPADD('MONTH', -1, CURRENT_TIMESTAMP()) AND CURRENT_TIMESTAMP();
SELECT * FROM car_order WHERE rownum < 3;
SELECT first_name, last_name FROM employee WHERE category_id = 2;
SELECT model FROM car_type WHERE brand = 'Honda';
SELECT model FROM car_type WHERE brand != 'Honda';

--complex conditions
--select suspension experts names
SELECT first_name, last_name FROM employee 
WHERE employee_category_id = SELECT employee_category_id FROM employee_category 
WHERE name = 'Suspension';

--select free non suspension experts
SELECT first_name, last_name FROM employee 
WHERE 
(employee_category_id != SELECT employee_category_id FROM employee_category WHERE name = 'Suspension') 
AND
(employee_id NOT IN (SELECT employee_id FROM employee_car_order));

--order by
SELECT * FROM car_order WHERE rownum <= 50 ORDER BY start_date;

--union
--get all contacts
SELECT first_name, last_name FROM employee
UNION
SELECT first_name, last_name FROM client;


--inner join
--get clients who are employees
SELECT clnt.first_name, clnt.last_name FROM client clnt
INNER JOIN employee empl
ON clnt.first_name = empl.first_name AND clnt.last_name = empl.last_name;

--count cars which have assigned employee
SELECT COUNT(ord.car_order_id) as tasks_in_progress FROM car_order ord
INNER JOIN employee_car_order emplord
ON ord.car_order_id=emplord.car_order_id;


--outer join
--get employee tasks distribution status
SELECT emp.first_name, emp.last_name, eco.car_order_id FROM employee_car_order eco
RIGHT JOIN employee emp
ON eco.employee_id = emp.employee_id;

SELECT emp.first_name, emp.last_name, eco.car_order_id FROM employee emp
LEFT JOIN employee_car_order eco
ON eco.employee_id = emp.employee_id;

--numeric functions
--lottery
--Does not work as expected
--SELECT first_name, last_name FROM client WHERE client_id = (MOD(ROUND(RAND()*10),5)+1);
--workaround:
SELECT first_name, last_name FROM client s JOIN 
(SELECT (MOD(RAND()*1000,5)+1) AS num FROM dual) rand 
ON client_id=rand.num; 

--string functions
--replace John Doe with Unknown
SELECT REPLACE(CONCAT(first_name,' ', last_name),'John Doe','Unknown') FROM client;

--calendar
--get days passed for each order
SELECT *,DATEDIFF('DAY', start_date, CURRENT_TIMESTAMP()) AS day_difference FROM car_order;

--aggragate
--get cars models count per brand
SELECT brand, COUNT (model) FROM car_type
GROUP BY brand;

--get orders with more than one resource working on
SELECT co.* FROM car_order co
JOIN
(SELECT car_order_id FROM employee_car_order
GROUP BY car_order_id
HAVING COUNT(car_order_id)>1) filter_eco
ON co.car_order_id = filter_eco.car_order_id;