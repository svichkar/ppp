CREATE TABLE car_type
(
car_type_id INT IDENTITY,
brand VARCHAR(255),
MODEL VARCHAR(255)
);

ALTER TABLE car_type ALTER COLUMN brand VARCHAR(255) NOT NULL;
ALTER TABLE car_type ALTER COLUMN model VARCHAR(255) NOT NULL;
ALTER TABLE car_type ADD CONSTRAINT unique_brand_model UNIQUE(brand, model);

CREATE TABLE client
(
client_id bigint IDENTITY,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL
);

CREATE TABLE car
(
car_id bigint PRIMARY KEY,
serial_id varchar_ignorecase(255) NOT NULL,
car_type_id INT NOT NULL,
client_id bigint NOT NULL,
FOREIGN KEY (car_type_id) REFERENCES car_type(car_type_id),
FOREIGN KEY (client_id) REFERENCES client(client_id)
);

CREATE TABLE car_order_status
(
car_order_status_id TINYINT IDENTITY,
name VARCHAR(255) NOT NULL
);

CREATE TABLE car_order
(
car_order_id BIGINT IDENTITY,
car_id BIGINT NOT NULL,
car_order_status_id TINYINT NOT NULL,
start_date TIMESTAMP NOT NULL,
end_date TIMESTAMP,
FOREIGN KEY (car_order_status_id) REFERENCES car_order_status (car_order_status_id),
FOREIGN KEY (car_id) REFERENCES car(car_id)
);

CREATE TABLE employee_category
(
employee_category_id TINYINT IDENTITY,
name VARCHAR (255) NOT NULL
);

CREATE TABLE employee
(
employee_id INT IDENTITY,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
employee_category_id TINYINT NOT NULL,
FOREIGN KEY (employee_category_id) REFERENCES employee_category(employee_category_id)
);
CREATE TABLE employee_car_order
(
employee_id INT NOT NULL,
car_order_id BIGINT NOT NULL,
PRIMARY KEY (employee_id,car_order_id),
FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
FOREIGN KEY (car_order_id) REFERENCES car_order(car_order_id)
);

ALTER TABLE car ALTER COLUMN car_id bigint AUTO_INCREMENT;
ALTER TABLE car_order_status ADD CONSTRAINT unique_car_order_status_name UNIQUE(name);
ALTER TABLE employee_category ADD CONSTRAINT unique_employee_category_name UNIQUE(name);
ALTER TABLE car ADD CONSTRAINT unique_car_serial_id UNIQUE(serial_id);

