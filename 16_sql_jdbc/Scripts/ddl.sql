CREATE TABLE author
(
author_id IDENTITY,
first_name VARCHAR (256) NOT NULL,
);

ALTER TABLE author
ADD COLUMN last_name
VARCHAR (256) NOT NULL;

CREATE TABLE category
(
category_id IDENTITY,
name VARCHAR (256) NOT NULL,
);

CREATE TABLE cell
(
cell_id IDENTITY,
name VARCHAR (256) NOT NULL,
);

CREATE TABLE client
(
client_id IDENTITY,
first_name VARCHAR (256) NOT NULL,
last_name VARCHAR (256) NOT NULL,
phone VARCHAR (256),
email VARCHAR (256),
);

CREATE TABLE book
(
book_id IDENTITY,
name VARCHAR (256) NOT NULL,
cell_id BIGINT,
category_id BIGINT,
FOREIGN KEY (cell_id) REFERENCES cell (cell_id),
FOREIGN KEY (category_id) REFERENCES category (category_id),
);

CREATE TABLE author_book
(
id IDENTITY,
author_id BIGINT,
book_id BIGINT,
FOREIGN KEY (book_id) REFERENCES book (book_id),
FOREIGN KEY (author_id) REFERENCES author (author_id),
);

CREATE TABLE rent_journal
(
TICKET_id IDENTITY,
book_id BIGINT,
client_id BIGINT,
rent_date DATE NOT NULL,
expired_date DATE NOT NULL,
return_date DATE,
FOREIGN KEY (book_id) REFERENCES book (book_id),
FOREIGN KEY (client_id) REFERENCES client (client_id),
);