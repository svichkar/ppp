--task 5
SELECT * FROM book;
SELECT * FROM client;
SELECT * FROM author;
SELECT name FROM category;
SELECT book_id, rent_date FROM rent_journal;
SELECT DISTINCT cell_id FROM book;
SELECT DISTINCT category_id FROM book;
--task 6
SELECT name FROM book WHERE cell_id = 1;
SELECT name FROM book WHERE cell_id > 1;
SELECT name, cell_id FROM book WHERE category_id BETWEEN 1 AND 3;
SELECT * FROM rent_journal WHERE return_date IS NULL;
SELECT * FROM author WHERE ROWNUM <= 5;
SELECT book_id FROM book WHERE name LIKE 'JAVA%';
SELECT * FROM book WHERE cell_id IN (1,3);
--task 7
--books in specific cell
SELECT name FROM book WHERE cell_id = (SELECT cell_id from cell where name = 'A1');
--client's contacts which have a book now
SELECT first_name, last_name, phone FROM client WHERE client_id IN(SELECT client_id from rent_journal WHERE return_date IS NULL);
--books by author
SELECT book_id,name FROM book WHERE book_id IN (SELECT book_id FROM author_book WHERE author_id IN (SELECT author_id FROM author WHERE last_name='Pushkin'));
--task 8
SELECT * FROM author ORDER BY last_name;
SELECT * FROM client ORDER BY first_name DESC;
SELECT book_id, name FROM book ORDER BY book_id DESC;
--task 9
SELECT * FROM rent_journal WHERE return_date IS NULL
UNION
SELECT * FROM rent_journal WHERE return_date = expired_date;
--task 10
SELECT b.name AS book_name, c.name AS category_name FROM book AS b, category AS c WHERE b.category_id = c.category_id;
--book with author
SELECT b.name AS book_name, temp.last_name, temp.first_name from book AS b
INNER JOIN (
SELECT ab.author_id, ab.book_id, a.last_name, a.first_name FROM author_book AS ab
INNER JOIN author AS a
ON a.author_id = ab.author_id
) AS temp
ON temp.book_id = b.book_id;
--task 11
--book with and without author
SELECT b.name AS book_name, temp.last_name, temp.first_name from book AS b
LEFT OUTER JOIN (
SELECT ab.author_id, ab.book_id, a.last_name, a.first_name FROM author_book AS ab
INNER JOIN author AS a
ON a.author_id = ab.author_id
) AS temp
ON temp.book_id = b.book_id;
--cell with and without book
SELECT c.name AS cell_name, b.name AS book_name FROM cell AS c
LEFT OUTER JOIN book as b
ON c.cell_id = b.cell_id;
--task 12
SELECT name, ROUND (book_id) FROM book;
--task 13
SELECT LENGTH(last_name), last_name FROM author;
--task 14
SELECT ticket_id, DAY_OF_WEEK (rent_date) from rent_journal;
--task 15
SELECT COUNT(book_id) FROM book GROUP BY cell_id HAVING cell_id = 1;
SELECT MAX (book_id) FROM book;