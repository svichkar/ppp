--simple select
SELECT * FROM book;
SELECT last_name, first_name FROM client;
SELECT DISTINCT author_id from author_book;

--select with conditions
SELECT name FROM book
WHERE name LIKE 'The%';

SELECT * FROM client
WHERE client_id BETWEEN 3 AND 5;

SELECT * FROM book
WHERE cell_id IN(2, 3);

SELECT * FROM rent_journal
WHERE return_date IS NULL;

SELECT DISTINCT author_id FROM author_book
WHERE book_id < 5 OR book_id = 7;

SELECT * FROM book
WHERE cell_id > 1 AND name LIKE 'The%';

SELECT book_id FROM rent_journal
WHERE rent_date < '2015-12-05';

SELECT * FROM author
WHERE first_name != 'Rick' AND first_name NOT LIKE 'M%';

--nested queries
SELECT book_id FROM author_book
WHERE author_id IN
	(SELECT author_id FROM author
	WHERE first_name = 'Rick' OR first_name LIKE 'M%');

--select name and phone of a user who hasn't rent any book yet
SELECT first_name, phone 
FROM client AS cl
WHERE NOT EXISTS (SELECT * FROM rent_journal AS rj 
				WHERE cl.client_id = rj.client_id);
	
--query with ORDER BY
SELECT last_name, first_name FROM author
ORDER BY last_name;

--query with UNION
SELECT book_id FROM book
WHERE name LIKE 'The%'
UNION
SELECT book_id FROM author_book
WHERE author_id BETWEEN 6 AND 9;


--INNER JOIN queries
--select all books and theirs categories
SELECT b.name, c.name
FROM book AS b
INNER JOIN category AS c
ON b.category_id = c.category_id;

--select all books names and theirs author's last name
SELECT a.last_name, b.name
FROM author_book AS ab
INNER JOIN author AS a ON ab.author_id = a.author_id
INNER JOIN book as b ON ab.book_id = b.book_id;

--select all the books which are stored in cell A
SELECT b.name, c.name
FROM book AS b
INNER JOIN cell AS c
ON b.cell_id = c.cell_id
WHERE c.name = 'cell A';

--OUTER JOIN queries
--select all clients and ids of the rented books
SELECT c.last_name, c.first_name, rj.book_id, rj.rent_date
FROM client as c
LEFT JOIN rent_journal AS rj ON rj.client_id = c.client_id;

--select all the books (names) and show client last name (if book in rent)
SELECT b.name, c.last_name 
FROM rent_journal AS rj
RIGHT JOIN book as b ON rj.book_id = b.book_id
LEFT JOIN client AS c ON rj.client_id = c.client_id;

--queries with numeric functions
--randomly propose a book which is not in rent
SELECT name
FROM book
WHERE book_id NOT IN
	(SELECT book_id FROM rent_journal
			WHERE return_date IS NOT NULL)
ORDER BY RAND()
LIMIT 1;

--queries with string functions
SELECT CONCAT(first_name, ' ', last_name, ', tel number: ', phone)
FROM client;

--queries with calendar functions
--how many time passed since books are in rent
SELECT book_id, DATEDIFF(dd, rent_date, GETDATE()) AS diff
FROM rent_journal
WHERE return_date IS NULL
ORDER BY diff DESC;

--queries with aggregate functions
--get category which has most of the books
SELECT COUNT(b.book_id) AS count, c.name
FROM book AS b
INNER JOIN category AS c ON b.category_id = c.category_id
GROUP BY c.category_id
ORDER BY count DESC;

--get authors who has less amount of books in the library
SELECT COUNT(ab.book_id) AS count, a.last_name
FROM author_book AS ab
INNER JOIN author as a ON ab.author_id = a.author_id
GROUP BY ab.author_id
HAVING count = MIN(count);