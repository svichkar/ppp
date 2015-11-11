SET SCHEMA sqllab;
--customer
INSERT INTO customer (first_name, last_name, phone) VALUES ('John', 'Hughes', '380663241432');
INSERT INTO customer (first_name, last_name, phone) VALUES ('Kenny', 'Smith', '380663325132');
INSERT INTO customer (first_name, last_name, phone) VALUES ('Leslie', 'Bottoms', '380672345132');
INSERT INTO customer (first_name, last_name, phone) VALUES ('Stacy', 'Kelling', '380963890222');
INSERT INTO customer (first_name, last_name, phone) VALUES ('Selesta', 'King', '380693324444');
--part
INSERT INTO part (part_name, vendor, amount) VALUES ('Large gear', 'KhMZ', 24);
INSERT INTO part (part_name, vendor, amount) VALUES ('Small gear', 'KhMZ', 124);
INSERT INTO part (part_name, vendor, amount) VALUES ('Hydraulics', 'KPZ', 17);
INSERT INTO part (part_name, vendor, amount) VALUES ('Valuable car part', 'MPZ', 6);
INSERT INTO part (part_name, vendor, amount) VALUES ('Lightbulbs', 'ZLZ', 46);
--status
INSERT INTO status (status_name) VALUES ('Idle');
INSERT INTO status (status_name) VALUES ('Busy');
INSERT INTO status (status_name) VALUES ('Vacation');
INSERT INTO status (status_name) VALUES ('Sick Leave');
--worker_specialization
INSERT INTO worker_specialization (specialization_name) VALUES ('Mechanic');
INSERT INTO worker_specialization (specialization_name) VALUES ('Worker');
INSERT INTO worker_specialization (specialization_name) VALUES ('Electrician');
INSERT INTO worker_specialization (specialization_name) VALUES ('Trainee');
--order_status
INSERT INTO order_status (order_status_name) VALUES ('Pending');
INSERT INTO order_status (order_status_name) VALUES ('In progress');
INSERT INTO order_status (order_status_name) VALUES ('Completed');
INSERT INTO order_status (order_status_name) VALUES ('Rejected');
--car
INSERT INTO car (model, vin, description, customer_id) VALUES ('Audi A8', 'Y5321UGKDS184KT51', null, 1);
INSERT INTO car (model, vin, description, customer_id) VALUES ('BMW X6', 'X7TUNS235SD41M631', 'Black', 3);
INSERT INTO car (model, vin, description, customer_id) VALUES ('BMW X5', 'X7HJST51M592VX342', 'Black', 3);
INSERT INTO car (model, vin, description, customer_id) VALUES ('Audi A6', 'Y509832JFDW15NS21', 'Green', 4);
INSERT INTO car (model, vin, description, customer_id) VALUES ('Dodge Charger', 'X0423NJFG2015GWE4', 'Red', 2);
INSERT INTO car (model, vin, description, customer_id) VALUES ('VAZ 2110', 'W24HJTR2556BMS1P6', 'White', 5);
--worker
INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('John', 'Johnes', 1, 1);
INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('Sam', 'Rockwell', 3, 1);
INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('Stan', 'Sikorsky', 2, 1);
INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('Kenny', 'Smiles', 4, 1);
INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('Jackson', 'Teller', 4, 1);
--order
INSERT INTO order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (1, 'Busted engine', 1, CURRENT_TIMESTAMP(), null);
INSERT INTO order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (1, 'Busted tires', 2, CURRENT_TIMESTAMP(), null);
INSERT INTO order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (1, 'Missing gears', 3, CURRENT_TIMESTAMP(), null);
INSERT INTO order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (1, 'Missing gears', 5, CURRENT_TIMESTAMP(), null);
INSERT INTO order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (1, 'Missing large gears', 4, CURRENT_TIMESTAMP(), null);
--order_worker
INSERT INTO order_worker VALUES (1, 1, 'N');
INSERT INTO order_worker VALUES (1, 5, 'N');
INSERT INTO order_worker VALUES (2, 1, 'N');
INSERT INTO order_worker VALUES (2, 2, 'N');
INSERT INTO order_worker VALUES (3, 5, 'N');
INSERT INTO order_worker VALUES (3, 2, 'N');
INSERT INTO order_worker VALUES (4, 4, 'N');
INSERT INTO order_worker VALUES (4, 3, 'N');
INSERT INTO order_worker VALUES (5, 1, 'N');
--order_part
INSERT INTO order_part VALUES (1, 4, 1);
INSERT INTO order_part VALUES (2, 4, 1);
INSERT INTO order_part VALUES (3, 1, 6);
INSERT INTO order_part VALUES (4, 1, 4);
INSERT INTO order_part VALUES (5, 2, 5);

UPDATE worker SET status_id = 2 WHERE worker_id = 1;
UPDATE worker SET status_id = 2 WHERE worker_id = 2;
UPDATE worker SET status_id = 4 WHERE worker_id = 3;
UPDATE worker SET status_id = 3 WHERE worker_id = 4;
UPDATE worker SET status_id = 2 WHERE worker_id = 5;

INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('Smiley', 'Face', 4, 1);
INSERT INTO worker (first_name, last_name, specialization_id, status_id) VALUES ('Kevin', 'Gross', 4, 1);
UPDATE worker SET status_id = 2 WHERE worker_id = 6;
UPDATE worker SET status_id = 2 WHERE worker_id = 7;
UPDATE order_worker SET worker_id = 6 where worker_id = 4;
UPDATE order_worker SET worker_id = 7 where worker_id = 5;
DELETE FROM worker WHERE worker_id = 4;
DELETE FROM worker WHERE worker_id = 5;