--5 selections
SELECT * FROM worker;
SELECT * FROM car;
SELECT spec_name FROM worker_specification;
SELECT part_name, vendor FROM part;
SELECT order_id, description FROM order_in_work;
--get all workers that assigned to orders(without duplications)
SELECT distinct f_name ||' '||  l_name FROM order_worker ow JOIN worker w ON ow.worker_id = w.worker_id;

--6 diff conditions
--get worker with last name starts with 'Sch'
SELECT * FROM worker where l_name like 'Sch%';
--get all orders with empty finished date
SELECT * FROM order_in_work WHERE datetime_end IS NULL;
SELECT * FROM order_in_work WHERE day_of_month(datetime_start) > 6;
--get all orders that were started within defined timespan
SELECT * FROM order_in_work WHERE datetime_start BETWEEN parsedatetime('2015-11-05', 'yyyy-MM-dd') AND parsedatetime('07-11-2015', 'dd-MM-yyyy');
--get all orders that require more than 10 parts
SELECT oiw.order_id, oiw.description, po.amount FROM order_in_work oiw JOIN part_order po ON oiw.order_id=po.order_id WHERE po.amount>10
--get all orders that require work with tires (tyres)
SELECT oiw.order_id, oiw.description, c.model  FROM order_in_work oiw JOIN car c ON oiw.car_id=c.car_id WHERE oiw.description LIKE '%tire%' OR oiw.description LIKE '%tyre%' ; 
SELECT oiw.order_id, oiw.description, c.model  FROM order_in_work oiw JOIN car c ON oiw.car_id=c.car_id WHERE length(c.vin)>17


--7 included requests
--get cars that have been in work since 06 November
SELECT model, vin FROM car WHERE car_id IN (SELECT car_id FROM order_in_work WHERE datetime_start>parsedatetime('06-11-2015', 'dd-MM-yyyy'));
--get only mechanics within workers
SELECT f_name, l_name FROM worker WHERE spec_id in (SELECT spec_id FROM worker_specification WHERE spec_name LIKE '%mechanic');
--get models of cars that are present on service station
SELECT distinct model FROM car WHERE car_id IN (SELECT car_id FROM order_in_work WHERE datetime_end IS NULL);

--8 with order by
SELECT part_name, vendor FROM part ORDER BY vendor ASC;
SELECT f_name ||' '|| l_name FROM worker ORDER BY l_name ASC;
SELECT order_id, datetime_start FROM order_in_work ORDER BY datetime_start  asc;

--9 using union
--get all workers and customers
SELECT f_name, l_name FROM worker
UNION
SELECT f_name, l_name FROM customer;
--get all vendors of parts and model of cars
SELECT model as PRODUCTOR FROM car
UNION
SELECT vendor as PRODUCTOR FROM part;

--10 using inner joins
--get all part that required for current orders
SELECT part_name FROM order_in_work oiw INNER JOIN  part_order po INNER JOIN part p ON po.order_id=oiw.order_id AND p.part_id=po.part_id WHERE oiw.datetime_end IS NULL;
--get all orders with status 'pending'
SELECT oiw.* FROM order_in_work oiw INNER JOIN order_status os ON oiw.order_status_id=os.order_status_id WHERE os.order_status_name IN ('pending');
--get all workers that finished with car in November
SELECT w.f_name||' '||w.l_name FROM order_in_work oiw INNER JOIN order_worker ow INNER JOIN worker w ON oiw.order_id=ow.order_id  AND ow.worker_id=w.worker_id
WHERE ow.completed=true AND month(oiw.datetime_end)=11;

--11 left/right/outer join
--find cars that are not at service station at all
SELECT c.model, c.vin FROM order_in_work oiw RIGHT JOIN car c ON oiw.car_id=c.car_id WHERE oiw.car_id IS NULL;
--get workers that are not assigned now
SELECT  w.f_name||' '||w.l_name AS not_assigned  FROM worker w  LEFT JOIN order_worker ow  ON w.worker_id=ow.worker_id WHERE ow.order_id IS NULL;
--get parts that are not in orders
SELECT p.part_name  FROM order_in_work oiw INNER  JOIN part_order po  ON oiw.order_id=po.order_id RIGHT OUTER JOIN part p
ON po.part_id=p.part_id WHERE po.order_id IS NULL;

--12 numeric functions
--get workers randomly
SELECT f_name, l_name FROM worker WHERE worker_id=round(rand()*10,0);
--get encrypted name of car
SELECT model, ENCRYPT('AES', '00', STRINGTOUTF8(model)) FROM car;

--13 string function
--show models of cars in low case
SELECT lower(model) FROM car;
--show BMW with B in capital only
SELECT replace(lower(model),'b', 'B') FROM car where model='BMW';
--show initials of workers
SELECT substr(f_name, 0, 1)||','||substr(l_name,0, 1)  AS initials FROM worker;

--14 dates
--get order that were added in first 5 days of November
SELECT * FROM order_in_work WHERE datetime_start>dateadd('DAY', 5, DATE '2015-11-01');
--get duration for processing all completed orders
SELECT datediff('HOUR', oiw .datetime_start, oiw.datetime_end) AS processing_time FROM order_in_work oiw WHERE datetime_end IS NOT NULL;

--15 aggregate
--get total amount of parts in store
SELECT part_name, sum(amount) FROM part GROUP BY part_name;
--get workers assigned on 2 and more orders
SELECT w.f_name, w.l_name FROM worker w INNER JOIN order_worker ow ON w.worker_id=ow.worker_id  GROUP BY w.worker_id HAVING count(*)>1;
--get all cars for each customer in one string
SELECT cu.f_name||' '||cu.l_name, GROUP_CONCAT(model ORDER BY car_id SEPARATOR '; ') FROM car cc INNER JOIN customer cu ON cc.customer_id=cu.customer_id GROUP BY (cu.customer_id);





