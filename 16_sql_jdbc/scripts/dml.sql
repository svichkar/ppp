INSERT INTO role (role_id, role_name) VALUES (1, 'manager');
INSERT INTO role (role_id, role_name) VALUES (2, 'client');

INSERT INTO user (user_id, login, password, role_id) VALUES (1, 'john', 'john',1);
INSERT INTO user (user_id, login, password, role_id) VALUES (2, 'jack', 'jack',2);
INSERT INTO user (user_id, login, password, role_id) VALUES (3, 'mich', 'mich',2);
INSERT INTO user (user_id, login, password, role_id) VALUES (4, 'mor', 'mor',1);
INSERT INTO user (user_id, login, password, role_id) VALUES (5, 'hom', 'hom',2);
INSERT INTO user (user_id, login, password, role_id) VALUES (6, 'stan', 'stan',2);

INSERT INTO client (first_name, last_name, user_id) VALUES ('John', 'Johnson',1);
INSERT INTO client (first_name, last_name, user_id) VALUES ('John', 'Jackson',2);
INSERT INTO client (first_name, last_name, user_id) VALUES ('Michael', 'Jackson',3);
INSERT INTO client (first_name, last_name, user_id) VALUES ('Morgen', 'Stern',4);
INSERT INTO client (first_name, last_name, user_id) VALUES ('Homer', 'Simpson',5);
INSERT INTO client (first_name, last_name, user_id) VALUES ('Stanislav', 'Piterskiy',6);

INSERT INTO car_type (brand, model_name) VALUES ('Skoda', 'Octavia');
INSERT INTO car_type (brand, model_name) VALUES ('Skoda', 'Fabia');
INSERT INTO car_type (brand, model_name) VALUES ('VAZ', '2101');
INSERT INTO car_type (brand, model_name) VALUES ('VAZ', '21110');
INSERT INTO car_type (brand, model_name) VALUES ('Mitsubishi', 'Lancer');
INSERT INTO car_type (brand, model_name) VALUES ('BMW', '520');
INSERT INTO car_type (brand, model_name) VALUES ('Plymouth', 'Valiant');
INSERT INTO car_type (brand, model_name) VALUES ('Lincoln', 'Town Car');

INSERT INTO car (car_id, serial_id, car_type_id, client_id) VALUES (10001,'55kkzz00', 8, 3);
INSERT INTO car (car_id, serial_id, car_type_id, client_id) VALUES (10002,'5223ðk07660', 7, 5);
INSERT INTO car (car_id, serial_id, car_type_id, client_id) VALUES (10003,'34rrdw34ghhj', 3, 6);
INSERT INTO car (car_id, serial_id, car_type_id, client_id) VALUES (10004,'zcvsr34rdsasd', 4, 6);
INSERT INTO car (car_id, serial_id, car_type_id, client_id) VALUES (10005,'235235343542j', 5, 4);

INSERT INTO employee_category (name) VALUES ('electricity');
INSERT INTO employee_category (name) VALUES ('engine');
INSERT INTO employee_category (name) VALUES ('tuning');
INSERT INTO employee_category (name) VALUES ('transmission');
INSERT INTO employee_category (name) VALUES ('chassis');

INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Worker', 'First', 1);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Worker', 'Second', 4);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Worker', 'Third', 5);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Super', 'Worker', 3);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Mega', 'Master', 2);

INSERT INTO car_order_status (name) VALUES ('Complete');
INSERT INTO car_order_status (name) VALUES ('In Progress');
INSERT INTO car_order_status (name) VALUES ('Pending');

INSERT INTO car_order (car_id, car_order_status_id, start_date) VALUES(10001, 2, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_id, car_order_status_id, start_date) VALUES(10002, 2, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_id, car_order_status_id, start_date) VALUES(10003, 2, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_id, car_order_status_id, start_date) VALUES(10005, 3, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_id, car_order_status_id, start_date) VALUES(10004, 2, CURRENT_TIMESTAMP());

INSERT INTO employee_car_order (identifier, employee_id, car_order_id) VALUES (50,1,2);
INSERT INTO employee_car_order (identifier, employee_id, car_order_id) VALUES (51,2,1);
INSERT INTO employee_car_order (identifier, employee_id, car_order_id) VALUES (52,3,3);
INSERT INTO employee_car_order (identifier, employee_id, car_order_id) VALUES (53,4,3);
INSERT INTO employee_car_order (identifier, employee_id, car_order_id) VALUES (54,5,5);

DELETE FROM employee_car_order WHERE employee_id = 5;
DELETE FROM employee WHERE first_name = 'Mega';

UPDATE car_order SET car_order_status_id = 1 WHERE car_order_id = 1;
UPDATE car_order SET end_date = CURRENT_TIMESTAMP() WHERE car_order_status_id = 1;
