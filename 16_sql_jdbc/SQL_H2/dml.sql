INSERT INTO car_type (brand,model)
VALUES ('Honda','Civic'), ('Honda','CR-V'), ('Lada','Kalina'), ('Hyundai','i30'), ('Toyota', 'Corolla');

INSERT INTO client (first_name, last_name)
VALUES ('Ivan','Lazy'), ('Ivan', 'Grozniy'), ('John', 'Doe'), ('Terry','Prachett'), ('Mike', 'Majestic'), ('Jimmy','Brown');

INSERT INTO car (serial_id, car_type_id, client_id)
VALUES 
('SN1422525262421',
SELECT car_type_id FROM car_type WHERE brand='Honda' AND model='CR-V',
SELECT client_id FROM client WHERE first_name='John' AND last_name = 'Doe'
),
('SN142277732422',
SELECT car_type_id FROM car_type WHERE brand='Lada' AND model='Kalina',
SELECT client_id FROM client WHERE first_name='Ivan' AND last_name = 'Grozniy'
),
('SN1422566732423',
SELECT car_type_id FROM car_type WHERE brand='Honda' AND model='CR-V',
SELECT client_id FROM client WHERE first_name='John' AND last_name = 'Doe'
),
('SN1422584732424',
SELECT car_type_id FROM car_type WHERE brand='Hyundai' AND model='i30',
SELECT client_id FROM client WHERE first_name='John' AND last_name = 'Doe'
),
('SN1422566732425',
SELECT car_type_id FROM car_type WHERE brand='Toyota' AND model='Corolla',
SELECT client_id FROM client WHERE first_name='Ivan' AND last_name = 'Lazy'
),
('SN1422566732426',
SELECT car_type_id FROM car_type WHERE brand='Honda' AND model='Civic',
SELECT client_id FROM client WHERE first_name='John' AND last_name = 'Doe'
),
('SN14253232427',
SELECT car_type_id FROM car_type WHERE brand='Honda' AND model='CR-V',
SELECT client_id FROM client WHERE first_name='John' AND last_name = 'Doe'
);

INSERT INTO car_order_status (name)
VALUES ('Complete'),('In Progress'),('Pending');

INSERT INTO employee_category (name)
VALUES ('Electric'),('Engine'),('Suspension'),('Trainee');

INSERT INTO employee (first_name, last_name, employee_category_id)
VALUES
(
'Vasiliy',
'Tesla',
SELECT employee_category_id FROM employee_category WHERE name='Electric'
),
(
'Dmitriy',
'Gasoline',
SELECT employee_category_id FROM employee_category WHERE name='Engine'
),

(
'Mark',
'Spencer',
SELECT employee_category_id FROM employee_category WHERE name='Suspension'
),

(
'Jimmy',
'Brown',
SELECT employee_category_id FROM employee_category WHERE name='Trainee'
);

INSERT INTO car_order (car_id, car_order_status_id, start_date)
VALUES
(
SELECT car_id FROM car WHERE serial_id='SN1422525262421',
SELECT car_order_status_id FROM car_order_status WHERE name='Pending',
CURRENT_TIMESTAMP()
),
(
SELECT car_id FROM car WHERE serial_id='SN142277732422',
SELECT car_order_status_id FROM car_order_status WHERE name='Pending',
CURRENT_TIMESTAMP()
),
(
SELECT car_id FROM car WHERE serial_id='SN1422566732423',
SELECT car_order_status_id FROM car_order_status WHERE name='Pending',
CURRENT_TIMESTAMP()
),
(
SELECT car_id FROM car WHERE serial_id='SN1422584732424',
SELECT car_order_status_id FROM car_order_status WHERE name='Pending',
CURRENT_TIMESTAMP()
),
(
SELECT car_id FROM car WHERE serial_id='SN1422566732425',
SELECT car_order_status_id FROM car_order_status WHERE name='Pending',
CURRENT_TIMESTAMP()
);


--Employee are assigned to the task
INSERT INTO employee_car_order (employee_id,car_order_id)
VALUES(2,1),(3,1),(3,3),(4,2);

--promote Jimmy and assign Mark's tasks
UPDATE employee
SET employee_category_id = SELECT employee_category_id FROM employee_category WHERE name='Suspension'
WHERE first_name = 'Jimmy' and last_name = 'Brown';

UPDATE employee_car_order
SET employee_id = SELECT employee_id FROM employee WHERE first_name = 'Jimmy' AND last_name = 'Brown'
WHERE employee_id = SELECT employee_id FROM employee WHERE first_name = 'Mark' AND last_name = 'Spencer';
 
--fire Mark Spencer
DELETE FROM employee WHERE first_name = 'Mark' AND last_name = 'Spencer';