-- 1st stage
CREATE TABLE customer (
customer_id INT IDENTITY,
first_name VARCHAR (25) NOT NULL,
last_name VARCHAR (25) NOT NULL,
phone VARCHAR (25));

CREATE TABLE part (
part_id BIGINT IDENTITY,
part_name VARCHAR (250) NOT NULL,
vendor VARCHAR (100) NOT NULL,
amount BIGINT NOT NULL);

CREATE TABLE status (
status_id INT IDENTITY,
status_name VARCHAR (100) NOT NULL);

CREATE TABLE worker_specialization (
specialization_id INT IDENTITY,
specialization_name VARCHAR (100) NOT NULL);

CREATE TABLE order_status (
order_status_id INT IDENTITY,
order_status_name VARCHAR (50) NOT NULL);

-- 2nd stage
CREATE TABLE car (
car_id INT IDENTITY,
model VARCHAR (100) NOT NULL,
vin VARCHAR (17) NOT NULL,
description VARCHAR (200) NOT NULL,
customer_id INT REFERENCES customer(customer_id));

CREATE TABLE worker (
worker_id INT IDENTITY,
first_name VARCHAR (25) NOT NULL,
last_name VARCHAR (25) NOT NULL,
specialization_id INT REFERENCES worker_specialization(specialization_id));

-- 3rd stage
CREATE TABLE worker_status (
worker_id INT REFERENCES worker(worker_id),
status_id INT REFERENCES status(status_id));

CREATE TABLE order_in_work (
order_id BIGINT IDENTITY,
order_status_id INT REFERENCES order_status(order_status_id),
description VARCHAR (255) NOT NULL,
car_id INT REFERENCES car(car_id),
timestamp_start TIMESTAMP NOT NULL,
timestamp_finish TIMESTAMP);

-- 4th stage
CREATE TABLE order_worker (
order_id BIGINT REFERENCES order_in_work(order_id),
worker_id INT NOT NULL,
is_completed VARCHAR (1) NOT NULL);

ALTER TABLE order_worker
ADD FOREIGN KEY (worker_id)
REFERENCES worker(worker_id);

CREATE TABLE order_part (
order_id BIGINT REFERENCES order_in_work(order_id),
part_id BIGINT NOT NULL,
used_amount BIGINT NOT NULL);

ALTER TABLE order_part
ADD FOREIGN KEY (part_id)
REFERENCES part(part_id);

ALTER TABLE worker_status
ADD CONSTRAINT worker_unique UNIQUE (worker_id);

ALTER TABLE order_worker
ADD CONSTRAINT order_worker_unique UNIQUE (order_id, worker_id);

ALTER TABLE order_part
ADD CONSTRAINT order_part_unique UNIQUE (order_id, part_id);