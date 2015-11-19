CREATE TABLE category (
id INT PRIMARY KEY,
name VARCHAR(30) UNIQUE
);
DROP TABLE category;
CREATE TABLE category (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) UNIQUE
);
CREATE TABLE book (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100), 
author VARCHAR(30),
publisher VARCHAR(50),
category_id NUMBER(3,0) NOT NULL ,
FOREIGN KEY (category_id) REFERENCES category (id)
);
ALTER TABLE book ALTER COLUMN author VARCHAR(50);

CREATE TABLE reader (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50),
adress VARCHAR(50)
);
CREATE TABLE book_instance (
id INT PRIMARY KEY AUTO_INCREMENT,
book_id INT,
inventory_number INT,
FOREIGN KEY (book_id) REFERENCES book  (id)
);
CREATE TABLE journal (
id INT PRIMARY KEY AUTO_INCREMENT,
book_instance_id INT NOT NULL,
reader_id INT NOT NULL, 
start_date DATE NOT NULL,
end_date DATE NOT NULL,
FOREIGN KEY (book_instance_id) REFERENCES book_instance  (id),
FOREIGN KEY (reader_id) REFERENCES reader  (id)
);
