SELECT * FROM order_in_work ;

-- select all orders in work
SELECT * FROM order_in_work WHERE order_status_id  = (
SELECT order_status_id FROM order_status WHERE order_status_name ='in work');

--select all electrics
SELECT first_name ,last_name FROM worker WHERE specialization_id =(
SELECT specialization_id FROM worker_specialization WHERE specialization_name ='electric');

SELECT DISTINCT last_name FROM worker ;

SELECT order_id FROM order_in_work WHERE datetime_finish IS NULL;

SELECT part_name,amount   FROM part WHERE amount BETWEEN 5 AND 10;

SELECT part_name FROM part WHERE part_name LIKE 'lamp%';

SELECT part_name,amount   FROM part WHERE amount IN(5,10);

SELECT * FROM part ORDER BY part_name ASC;

SELECT olw.order_status_id  AS osi, olw.description AS desc FROM order_in_work as olw;

-- select all orders in work
SELECT olw.order_id , olw.description, olw.order_status_id   FROM order_in_work  olw
INNER JOIN order_status  os
ON olw.order_status_id   = os.order_status_id AND order_status_name ='in work';

SELECT *   FROM order_in_work  olw
LEFT OUTER JOIN order_status  os
ON olw.order_status_id   = os.order_status_id;

SELECT order_id ,amount ,part_id , MAX(amount )   FROM part_order  
GROUP BY order_id;

SELECT first_name ,last_name    FROM customer 
UNION
SELECT first_name ,last_name    FROM worker  ;

SELECT first_name ,last_name    FROM customer 
UNION ALL
SELECT first_name ,last_name    FROM worker  ;

SELECT part_name ,vendor, AVG(amount ) FROM part 
GROUP BY part_name ;

SELECT worker_id,specialization_id  , CONCAT_WS(' ',first_name, last_name ) FROM worker 
GROUP BY worker_id;

SELECT part_name ,vendor, AVG(amount ) avg_amount FROM part 
GROUP BY part_name 
HAVING avg_amount <>'6';