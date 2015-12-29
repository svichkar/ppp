CREATE TABLE car_type (
car_type_id INT IDENTITY,
brand VARCHAR(256) NOT NULL,
model_name VARCHAR(256) NOT NULL
);

CREATE TABLE client (
client_id BIGINT IDENTITY,
first_name VARCHAR(256) NOT NULL,
last_name VARCHAR(256) NOT NULL
);

CREATE TABLE employee_category (
employee_category_id TINYINT IDENTITY,
name VARCHAR(256) NOT NULL
);

CREATE TABLE car (
car_id BIGINT PRIMARY KEY,
serial_id VARCHAR_IGNORECASE(256) NOT NULL,
car_type_id INT,
client_id BIGINT,
FOREIGN KEY (car_type_id) REFERENCES car_type (car_type_id),
FOREIGN KEY (client_id) REFERENCES client (client_id)
);

CREATE TABLE employee (
employee_id INT IDENTITY,
first_name VARCHAR(256) NOT NULL,
last_name VARCHAR(256) NOT NULL,
employee_category_id TINYINT,
FOREIGN KEY (employee_category_id) REFERENCES employee_category (employee_category_id)
);

CREATE TABLE car_order (
car_order_id BIGINT IDENTITY,
car_id BIGINT,
car_order_status_id TINYINT,
start_date TIMESTAMP NOT NULL,
end_date TIMESTAMP,
);

ALTER TABLE car_order
ADD FOREIGN KEY (car_id)
REFERENCES car (car_id);

CREATE TABLE car_order_status (
car_order_status_id TINYINT IDENTITY,
name VARCHAR(100) NOT NULL,
);

ALTER TABLE car_order
ADD FOREIGN KEY (car_order_status_id)
REFERENCES car_order_status (car_order_status_id);

CREATE TABLE employee_car_order (
employee_id INT,
car_order_id BIGINT,
FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
FOREIGN KEY (car_order_id) REFERENCES car_order (car_order_id)
);

ALTER TABLE car_type
ADD CONSTRAINT unique_brand_model UNIQUE(brand, model_name);