INSERT INTO role (role_name) VALUES ('manager');
INSERT INTO role (role_name) VALUES ('client');

INSERT INTO user (login, password, role_id) VALUES ('john', 'john',2);
INSERT INTO user (login, password, role_id) VALUES ('jack', 'jack',2);
INSERT INTO user (login, password, role_id) VALUES ('mich', 'mich',2);
INSERT INTO user (login, password, role_id) VALUES ('mor', 'mor',1);
INSERT INTO user (login, password, role_id) VALUES ('hom', 'hom',2);
INSERT INTO user (login, password, role_id) VALUES ('stan', 'stan',2);

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

INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('aaaaaaaaaa', 8, 3);
INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('bbbbbbbbbb', 7, 5);
INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('cccccccccc', 3, 6);
INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('xxxxxxxxxx', 4, 6);
INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('yyyyyyyyyy', 5, 4);
INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('zzz', 5, 4);
INSERT INTO car (serial_vin, car_type_id, client_id) VALUES ('mmmmmmm', 5, 4);

INSERT INTO employee_category (employee_category_name) VALUES ('electricity');
INSERT INTO employee_category (employee_category_name) VALUES ('engine');
INSERT INTO employee_category (employee_category_name) VALUES ('tuning');
INSERT INTO employee_category (employee_category_name) VALUES ('transmission');
INSERT INTO employee_category (employee_category_name) VALUES ('chassis');

INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Vit', 'Ryb', 1);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Serg', 'Koz', 4);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Jax', 'Kryzh', 5);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Max', 'Novikov', 3);
INSERT INTO employee (first_name, last_name, employee_category_id) VALUES ('Kate', 'Ginger', 2);

INSERT INTO car_order_status (car_order_status_name) VALUES ('Complete');
INSERT INTO car_order_status (car_order_status_name) VALUES ('In Progress');
INSERT INTO car_order_status (car_order_status_name) VALUES ('Pending');

INSERT INTO car_order (car_car_id, car_order_status_id, start_date) VALUES(1, 2, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_car_id, car_order_status_id, start_date) VALUES(2, 2, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_car_id, car_order_status_id, start_date) VALUES(3, 2, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_car_id, car_order_status_id, start_date) VALUES(4, 3, CURRENT_TIMESTAMP());
INSERT INTO car_order (car_car_id, car_order_status_id, start_date) VALUES(5, 2, CURRENT_TIMESTAMP());

INSERT INTO employee_car_order (employee_id, car_order_id) VALUES (1,2);
INSERT INTO employee_car_order (employee_id, car_order_id) VALUES (2,1);
INSERT INTO employee_car_order (employee_id, car_order_id) VALUES (3,3);
INSERT INTO employee_car_order (employee_id, car_order_id) VALUES (4,3);
INSERT INTO employee_car_order (employee_id, car_order_id) VALUES (5,5);
