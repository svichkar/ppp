--define schema
SET SCHEMA sqllab;
---fill table role
INSERT INTO role (rolename) VALUES ('admin');
INSERT INTO role (rolename) VALUES ('user');
---fill table customer
INSERT INTO customer (f_name,l_name, phone) VALUES ('Hans', 'Bernanrd', '+44080045456');
INSERT INTO customer (f_name,l_name, phone) VALUES ('Dini', 'Omar', '+44080045457');
INSERT INTO customer (f_name,l_name, phone) VALUES ('Ivan', 'Stuart', '+44080045458');
INSERT INTO customer (f_name,l_name, phone) VALUES ('David', 'Cox', '+44080045458');
INSERT INTO customer (f_name,l_name, phone) VALUES ('David', 'Box', '+44080045458');
---fill table user
INSERT INTO user (username, password, role_id, customer_id) VALUES ('hansb', '1', 2, 1);
INSERT INTO user (username, password, role_id, customer_id) VALUES ('dinio', '1', 2, 2);
INSERT INTO user (username, password, role_id) VALUES ('admin', '1', 1);
--fill table status
INSERT INTO status (status_name) VALUES ('Vacation');
INSERT INTO status (status_name) VALUES ('Busy');
INSERT INTO status (status_name) VALUES ('DayOff');
INSERT INTO status (status_name) VALUES ('Free');
INSERT INTO status (status_name) VALUES ('SickLeave');
--fill table worker_specification
INSERT INTO workerspecification (spec_name) VALUES ('electrician');
INSERT INTO workerspecification (spec_name) VALUES ('engine mechanic');
INSERT INTO workerspecification (spec_name) VALUES ('welder');
INSERT INTO workerspecification (spec_name) VALUES ('car suspension mechanic');
INSERT INTO workerspecification (spec_name) VALUES ('programmist');
--fill table order_status
INSERT INTO orderstatus (order_status_name) VALUES ('pending');
INSERT INTO orderstatus (order_status_name) VALUES ('work_in_progress');
INSERT INTO orderstatus (order_status_name) VALUES ('completed');
INSERT INTO orderstatus (order_status_name) VALUES ('cancelled');
INSERT INTO orderstatus (order_status_name) VALUES ('refused');
--fill table part
INSERT INTO part (part_name, vendor, amount) VALUES ('piston','AUDI', 40);
INSERT INTO part (part_name, vendor, amount) VALUES ('rod','BMW', 40);
INSERT INTO part (part_name, vendor, amount) VALUES ('ring','AUDI', 120);
INSERT INTO part (part_name, vendor, amount) VALUES ('pipe','VW', 10);
INSERT INTO part (part_name, vendor, amount) VALUES ('valve','BMW', 160);
INSERT INTO part (part_name, vendor, amount) VALUES ('bulb','VW', 100);
INSERT INTO part (part_name, vendor, amount) VALUES ('join','VW', 50);
INSERT INTO part (part_name, vendor, amount) VALUES ('speed box','BMW', 3);
INSERT INTO part (part_name, vendor, amount) VALUES ('chain','VW', 10);
INSERT INTO part (part_name, vendor, amount) VALUES ('chain','FIAT', 10);
INSERT INTO part (part_name, vendor, amount) VALUES ('valve','VAZ', 200);
INSERT INTO part (part_name, vendor, amount) VALUES ('rod','VAZ', 40);
--fill table car
INSERT INTO car (model, vin, description, customer_id) VALUES ('BMW','SUPT3456DF3456GG1','red color', 1);
INSERT INTO car (model, vin, description, customer_id) VALUES ('VW','FFRT3456DF3456VW1','green color', 2);
INSERT INTO car (model, vin, description, customer_id) VALUES ('VW','FFRT3456DF3456VW2','blue color', 2);
INSERT INTO car (model, vin, description, customer_id) VALUES ('DEAWOO','SUPT3456DF1111GG1','white color', 2);
INSERT INTO car (model, vin, description, customer_id) VALUES ('VAZ','EEEDD3456DF1111GG1','white color', 3);
INSERT INTO car (model, vin, description, customer_id) VALUES ('VOLVO','VVVVV3456DF1111GG1','black color', 4);
INSERT INTO car (model, vin, description, customer_id) VALUES ('DODGE','DODD3456DF1111GG1','silver color', 5);
--fill table worker
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Otto', 'Schultz', 1, 1);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Hanz', 'Goldberg', 2, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Axcel', 'Mittelvagen', 3, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Kurt', 'Schwidt', 4, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Adolf', 'Hitler', 5, 2);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Klaus', 'Mayer', 5, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Wilko', 'Muller', 5, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Uma', 'Hodfrig', 3, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Max', 'Wichtig', 1, 4);
INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES ('Gustaf', 'Wocht', 1, 4);
--fill table order_in_work
INSERT INTO orderinwork (order_status_id, description, car_id, datetime_start) VALUES (1, 'Need to change tires', 1, parsedatetime('2015-11-07 11:00:00', 'yyyy-MM-dd hh:mm:ss'));
INSERT INTO orderinwork (order_status_id, description, car_id, datetime_start) VALUES (2, 'knoking valves', 2, parsedatetime('2015-11-06 11:00:00', 'yyyy-MM-dd hh:mm:ss'));
INSERT INTO orderinwork (order_status_id, description, car_id, datetime_start) VALUES (2, 'replacement chain', 3, parsedatetime('2015-11-05 11:30:00', 'yyyy-MM-dd hh:mm:ss'));
INSERT INTO orderinwork (order_status_id, description, car_id, datetime_start) VALUES (2, 'replacement pipe', 4, parsedatetime('2015-11-05 11:30:00', 'yyyy-MM-dd hh:mm:ss'));
INSERT INTO orderinwork (order_status_id, description, car_id, datetime_start, datetime_end) VALUES (5, 'brocken engine', 5, parsedatetime('2015-11-05 11:30:00', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2015-11-08 11:30:00', 'yyyy-MM-dd hh:mm:ss'));
INSERT INTO orderinwork (order_status_id, description, car_id, datetime_start, datetime_end) VALUES (3, 'service at 50k km', 4, parsedatetime('2015-11-07 09:30:00', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2015-11-07 14:30:00', 'yyyy-MM-dd hh:mm:ss'));
--fill table order_worker
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (4, 1, false);
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (2, 2, false);
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (2, 3, false);
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (3, 4, false);
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (2, 5, true);
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (3, 6, true);
INSERT INTO orderworker (worker_id, order_id, completed) VALUES (4, 6, true);
--fill table part_order
INSERT INTO partorder (order_id, part_id, amount) VALUES (1,7,16);
INSERT INTO partorder (order_id, part_id, amount) VALUES (2,5,16);
INSERT INTO partorder (order_id, part_id, amount) VALUES (3,9,1);
INSERT INTO partorder (order_id, part_id, amount) VALUES (4,4,1);
INSERT INTO partorder (order_id, part_id, amount) VALUES (5,8,1);







