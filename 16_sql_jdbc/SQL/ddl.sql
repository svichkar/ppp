--create schema
CREATE SCHEMA sqllab;
--define schema
SET SCHEMA sqllab;

---creation tables only with primary keys
CREATE TABLE car
(
	car_id bigint IDENTITY,
	model VARCHAR(256) NOT NULL,
	vin VARCHAR(256) NOT NULL,
	description VARCHAR(256)
);

CREATE TABLE customer
(
	customer_id bigint IDENTITY,
	f_name VARCHAR(256) NOT NULL,
	l_name VARCHAR(256) NOT NULL,
	phone VARCHAR(30)
);

CREATE TABLE status
(
	status_id tinyint IDENTITY,
	status_name VARCHAR(256) NOT NULL
);

CREATE TABLE part
(
	part_id bigint IDENTITY,
	part_name VARCHAR(256) NOT NULL,
	vendor VARCHAR(256) NOT NULL,
	amount bigint NOT NULL
);


CREATE TABLE worker
(
	worker_id bigint IDENTITY,
	f_name VARCHAR(256) NOT NULL,
	l_name VARCHAR(256) NOT NULL,
);

CREATE TABLE worker_specification
(
	spec_id bigint IDENTITY,
	spec_name VARCHAR(256) NOT NULL
);

CREATE TABLE order_status
(
	order_status_id tinyint IDENTITY,
	order_status_name VARCHAR(256) NOT NULL
);

CREATE TABLE order_in_work
(
	order_id bigint IDENTITY,
	description VARCHAR(256),
	datetime_start TIMESTAMP NOT NULL,
	datetime_end TIMESTAMP
);

CREATE TABLE order_worker
(
	worker_id bigint,
	order_id bigint,
	completed BOOLEAN
);

CREATE TABLE part_order
(
	order_id bigint,
	part_id bigint,
	amount bigint NOT NULL
);

-----add tables with foreign keys and keys into existing tables----
ALTER TABLE car ADD COLUMN customer_id bigint;
ALTER TABLE car	ADD FOREIGN KEY(customer_id) REFERENCES customer(customer_id);

ALTER TABLE order_in_work ADD COLUMN car_id bigint;
ALTER TABLE order_in_work ADD FOREIGN KEY(car_id) REFERENCES car(car_id);

ALTER TABLE order_in_work ADD COLUMN order_status_id tinyint;
ALTER TABLE order_in_work ADD FOREIGN KEY(order_status_id) REFERENCES order_status(order_status_id);

ALTER TABLE order_worker ADD FOREIGN KEY(worker_id) REFERENCES worker(worker_id);
ALTER TABLE order_worker ADD FOREIGN KEY(order_id) REFERENCES order_in_work(order_id);

ALTER TABLE worker ADD COLUMN spec_id bigint;
ALTER TABLE worker ADD FOREIGN KEY(spec_id) REFERENCES worker_specification(spec_id);

ALTER TABLE worker ADD COLUMN status_id tinyint;
ALTER TABLE worker ADD FOREIGN KEY (status_id) REFERENCES status(status_id);

ALTER TABLE part_order ADD FOREIGN KEY(order_id) REFERENCES order_in_work(order_id);
ALTER TABLE part_order ADD FOREIGN KEY(part_id) REFERENCES part(part_id);


---add UNIQUE constraints
ALTER TABLE order_worker ADD CONSTRAINT UNIQUE_worker_in_order UNIQUE(order_id, worker_id); 
ALTER TABLE part_order ADD CONSTRAINT unigue_part_in_order UNIQUE(order_id, part_id);



