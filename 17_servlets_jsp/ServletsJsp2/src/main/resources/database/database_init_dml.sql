INSERT INTO web_role (web_role_name)VALUES ('admin'),('user'),('employee'),('manager');
INSERT INTO web_user (web_user_login, web_user_password, web_role_id) VALUES ('admin','admin', SELECT web_role_id FROM web_role WHERE web_role_name = 'admin');
INSERT INTO car_order_status (name) VALUES ('new'),('in progress'),('done'),('canceled');