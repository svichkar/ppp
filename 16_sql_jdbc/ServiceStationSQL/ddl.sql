CREATE TABLE worker_specialization
(
 specialization_id INT IDENTITY,
specialization_name VARCHAR(256) NOT NULL
);

CREATE TABLE worker_status
(
 worker_status_id INT IDENTITY,
 worker_status_name VARCHAR(128) NOT NULL
);

CREATE TABLE worker
(
 worker_id INT IDENTITY,
 specialization_id INT NOT NULL,
FOREIGN KEY (specialization_id) REFERENCES  worker_specialization(specialization_id),
first_name VARCHAR(128) NOT NULL,
last_name VARCHAR(128) NOT NULL,
 worker_status_id INT NOT NULL,
FOREIGN KEY (worker_status_id) REFERENCES  worker_status(worker_status_id),);


CREATE TABLE order_in_work
(
 order_id INT IDENTITY,
 order_description VARCHAR(512) NOT NULL,
 datetime_start TIMESTAMP NOT NULL,
 datetime_finish TIMESTAMP,
);

CREATE TABLE order_status
(
 order_status_id INT IDENTITY,
 order_status_name VARCHAR(128) NOT NULL
);

CREATE TABLE part
(
 part_id INT IDENTITY,
 part_name VARCHAR(128) NOT NULL,
 vendor VARCHAR(128) NOT NULL,
 amount TINYINT,
);

CREATE TABLE part_order
(
 order_id INT NOT NULL,
FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id),
 part_id INT NOT NULL,
FOREIGN KEY (part_id) REFERENCES  part(part_id),
 amount TINYINT
);

CREATE TABLE car
(
 car_id INT IDENTITY,
 car_model VARCHAR(128) NOT NULL,
 vin_number VARCHAR(17) NOT NULL UNIQUE,
 car_description VARCHAR(256),
);

CREATE TABLE customer
(
 customer_id INT IDENTITY,
first_name VARCHAR(128) NOT NULL,
last_name VARCHAR(128) NOT NULL,
phone VARCHAR(32),
);

CREATE TABLE order_worker
(
 order_id INT NOT NULL,
FOREIGN KEY (order_id) REFERENCES  order_in_work(order_id),
 worker_id INT IDENTITY,
FOREIGN KEY (worker_id) REFERENCES  worker(worker_id),
 isCompleted BOOLEAN NOT NULL DEFAULT false
);


ALTER TABLE order_in_work
ADD COLUMN order_status_id INT NOT NULL;

ALTER TABLE order_in_work 
   ADD FOREIGN KEY(order_status_id ) REFERENCES order_status (order_status_id );

   
ALTER TABLE order_in_work
ADD COLUMN car_id INT NOT NULL;

ALTER TABLE order_in_work 
   ADD FOREIGN KEY(car_id ) REFERENCES car (car_id );


ALTER TABLE car
ADD COLUMN customer_id INT NOT NULL;

ALTER TABLE car 
   ADD FOREIGN KEY(customer_id ) REFERENCES customer (customer_id );
   
CREATE INDEX order_idx ON order_in_work(order_id);

CREATE INDEX vendorx ON part(vendor);

CREATE INDEX vin_numberx ON car(vin_number);

CREATE INDEX last_namex ON customer(last_name);
CREATE INDEX phonex ON customer(phone);

/*DROP TABLE car ,customer ,order_in_work,order_status ,order_worker ,part ,part_order ,worker ,worker_specialization ,worker_status */