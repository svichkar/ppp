CREATE TABLE author
(
author_id INT IDENTITY,
first_name VARCHAR(256) NOT NULL
);

ALTER TABLE author
ADD COLUMN last_name VARCHAR(256) NOT NULL;

CREATE TABLE cell
(
cell_id IDENTITY,
name CHAR(128)
);

ALTER TABLE cell
ALTER COLUMN name VARCHAR(256) NOT NULL;

CREATE TABLE category
(
category_id IDENTITY,
name VARCHAR(256) NOT NULL
);

CREATE TABLE client
(
client_id IDENTITY,
first_name VARCHAR(256) NOT NULL,
last_name VARCHAR(256) NOT NULL,
phone VARCHAR(256),
email VARCHAR(256)
);

CREATE TABLE book
(
book_id IDENTITY,
name VARCHAR(256) NOT NULL,
cell_id BIGINT NOT NULL,
category_id BIGINT NOT NULL,
FOREIGN KEY (cell_id) REFERENCES cell (cell_id),
FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE author_book
(
author_id BIGINT NOT NULL,
book_id BIGINT NOT NULL,
FOREIGN KEY (author_id) REFERENCES author (author_id),
FOREIGN KEY (book_id) REFERENCES book (book_id)
);

CREATE TABLE rent_journal
(
ticket_id IDENTITY,
client_id BIGINT NOT NULL,
book_id BIGINT NOT NULL,
rent_date DATE NOT NULL,
expired_date DATE NOT NULL,
return_date DATE,
FOREIGN KEY (client_id) REFERENCES client (client_id),
FOREIGN KEY (book_id) REFERENCES book (book_id)
);

ALTER TABLE rent_journal
DROP COLUMN expired_date;